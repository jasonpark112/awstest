package dev.service.newsscrap.service;

import dev.service.newsscrap.entity.News;

import java.util.List;

public interface NewsService {
    Long save(News news);
    News findById(Long id);
    List<News> findByKeyword(String keyword);
}
