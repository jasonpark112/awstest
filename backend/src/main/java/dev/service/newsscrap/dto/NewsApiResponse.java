package dev.service.newsscrap.dto;

import dev.service.newsscrap.entity.News;
import lombok.*;

import java.util.List;

@Getter
@ToString
@AllArgsConstructor
@Builder
public class NewsApiResponse {
    private int total;
    private int start;
    private int display;
    List<News> news;
}
