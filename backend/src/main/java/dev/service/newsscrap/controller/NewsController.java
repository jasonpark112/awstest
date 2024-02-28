package dev.service.newsscrap.controller;

import dev.service.newsscrap.dto.NewsRequest;
import dev.service.newsscrap.entity.News;
import dev.service.newsscrap.exception.NotExistDataException;
import dev.service.newsscrap.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/news")
public class NewsController {

    private final NewsService newsService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody NewsRequest newsRequest) {
        News news = newsRequest.toEntity();

        Long id = newsService.save(news);

        return ResponseEntity.ok(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            News news = newsService.findById(id);

            return ResponseEntity.ok(news);
        } catch (NotExistDataException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getByKeyword(@RequestParam(name = "keyword", required = true, defaultValue = "") String keyword,
                                          @RequestParam(name = "page", required = false, defaultValue = "1") int page,
                                          @RequestParam(name = "size", required = false, defaultValue = "100") int size) {

            List<News> news = newsService.findByKeyword(keyword);

            return ResponseEntity.ok(news);

    }


}
