package com.netflix.netflix_clone.dto;

import com.netflix.netflix_clone.entity.Content;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ContentResponse {
    private Long id;
    private String title;
    private String description;
    private String thumbnailUrl;
    private LocalDateTime createdAt;

    public static ContentResponse from(Content content) {
        return new ContentResponse(
                content.getId(),
                content.getTitle(),
                content.getDescription(),
                content.getThumbnailUrl(),
                content.getCreatedAt()
        );
    }
}