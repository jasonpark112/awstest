package dev.service.newsscrap.dto;

import dev.service.newsscrap.entity.Scrap;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter
public class CustomScrapResponseDTO {
    private Long newsId;
    private String url;
    private String title;
    private String content;
    private String comment;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;

    public static CustomScrapResponseDTO from(Scrap scrap) {
        return CustomScrapResponseDTO.builder()
                .newsId(scrap.getNews().getId())
                .url(scrap.getNews().getLink())
                .title(scrap.getNews().getTitle())
                .content(scrap.getNews().getDescription())
                .comment(scrap.getComment())
                .createdTime(scrap.getCreatedTime())
                .updatedTime(scrap.getUpdatedTime())
                .build();
    }
}
