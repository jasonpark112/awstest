package dev.service.newsscrap.repository;

import dev.service.newsscrap.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NewsRepository extends JpaRepository<News, Long> {
    Optional<News> findById(Long id);
    List<News> findByKeyword(String keyword);
}