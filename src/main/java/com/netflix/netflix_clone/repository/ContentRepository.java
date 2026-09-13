package com.netflix.netflix_clone.repository;

import com.netflix.netflix_clone.entity.Content;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentRepository extends JpaRepository<Content, Long> {
}