package com.netflix.netflix_clone.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ContentRequest {
    private String title;
    private String description;
    private String thumbnailUrl;
}