package dev.service.newsscrap.service;

import dev.service.newsscrap.entity.News;
import dev.service.newsscrap.exception.NotExistDataException;
import dev.service.newsscrap.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;

    @Override
    public Long save(News news) {
        News savedNews = newsRepository.save(news);
        return savedNews.getId();
    }

    @Override
    public News findById(Long id) {
        return newsRepository.findById(id).orElseThrow(() -> new NotExistDataException("존재하지 않는 기사입니다."));
    }

    @Override
    public List<News> findByKeyword(String keyword) {
        return newsRepository.findByKeyword(keyword);
    }
}
