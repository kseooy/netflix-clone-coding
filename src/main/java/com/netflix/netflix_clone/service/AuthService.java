package com.netflix.netflix_clone.service;

import com.netflix.netflix_clone.jwt.JwtTokenProvider;
import com.netflix.netflix_clone.dto.LoginRequest;
import com.netflix.netflix_clone.dto.SignUpRequest;
import com.netflix.netflix_clone.dto.TokenResponse;
import com.netflix.netflix_clone.entity.Role;
import com.netflix.netflix_clone.entity.User;
import com.netflix.netflix_clone.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    // 회원가입
    @Transactional
    public String signUp(SignUpRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }

        Role role = request.getRole() != null ? request.getRole() : Role.USER;

        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword())) // 비밀번호 암호화
                .role(role)
                .build();

        userRepository.save(user);
        return "회원가입이 완료되었습니다.";
    }

    // 로그인 (JWT 토큰 발급)
    @Transactional(readOnly = true)
    public TokenResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 이메일입니다."));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        String token = jwtTokenProvider.createToken(user.getEmail(), user.getRole());
        return new TokenResponse(token);
    }
}