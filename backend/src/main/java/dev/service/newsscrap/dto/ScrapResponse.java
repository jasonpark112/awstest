package dev.service.newsscrap.dto;

import dev.service.newsscrap.entity.News;
import dev.service.newsscrap.entity.Scrap;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class ScrapResponse {
    private Long id;
    private News news;
    private String title;
    private String content;
    private String comment;
    private String keyword;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;

    public static ScrapResponse toDTO(Scrap scrap) {
        final Long id = scrap.getId();
        final News news = scrap.getNews();
        final String title = scrap.getTitle();
        final String content = scrap.getContent();
        final String comment = scrap.getComment();
        final String keyword = scrap.getNews().getKeyword();
        final LocalDateTime createdTime = scrap.getCreatedTime();
        final LocalDateTime updatedTime = scrap.getUpdatedTime();

        return new ScrapResponse(id, news, title, content, comment, keyword, createdTime, updatedTime);
    }
}
