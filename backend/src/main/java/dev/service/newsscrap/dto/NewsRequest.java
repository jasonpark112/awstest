package dev.service.newsscrap.dto;

import dev.service.newsscrap.entity.News;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NewsRequest {
    private String title;
    private String originallink;
    private String link;
    private String description;
    private String keyword;
    private String pubDate;

    public News toEntity() {
        return News.from(this);
    }

}
