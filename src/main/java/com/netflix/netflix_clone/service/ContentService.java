package com.netflix.netflix_clone.service;

import com.netflix.netflix_clone.dto.ContentRequest;
import com.netflix.netflix_clone.dto.ContentResponse;
import com.netflix.netflix_clone.entity.Content;
import com.netflix.netflix_clone.repository.ContentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContentService {

    private final ContentRepository contentRepository;

    // 생성 (ADMIN만)
    @Transactional
    public ContentResponse createContent(ContentRequest request) {
        Content content = Content.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .thumbnailUrl(request.getThumbnailUrl())
                .build();
        return ContentResponse.from(contentRepository.save(content));
    }

    // 전체 조회 (모든 사용자 허용)
    @Transactional(readOnly = true)
    public List<ContentResponse> getAllContents() {
        return contentRepository.findAll().stream()
                .map(ContentResponse::from)
                .toList();
    }

    // 단건 조회 (모든 사용자 허용)
    @Transactional(readOnly = true)
    public ContentResponse getContent(Long id) {
        Content content = contentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 콘텐츠입니다."));
        return ContentResponse.from(content);
    }

    // 수정 (ADMIN만)
    @Transactional
    public ContentResponse updateContent(Long id, ContentRequest request) {
        Content content = contentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 콘텐츠입니다."));
        content.update(request.getTitle(), request.getDescription(), request.getThumbnailUrl());
        return ContentResponse.from(content);
    }

    // 삭제 (ADMIN만)
    @Transactional
    public void deleteContent(Long id) {
        contentRepository.deleteById(id);
    }
}