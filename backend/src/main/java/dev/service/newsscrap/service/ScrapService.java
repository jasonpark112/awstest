package dev.service.newsscrap.service;

import dev.service.newsscrap.dto.CustomScrapResponseDTO;
import dev.service.newsscrap.dto.ScrapRequest;
import dev.service.newsscrap.entity.Scrap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ScrapService {

    Scrap findById(Long id);
    List<Scrap> findAll();

    Scrap save(ScrapRequest scrapRequest);

    Scrap update(Long id, Scrap scrap);

    void deleteById(Long scrapId, Long memberId);

    Page<CustomScrapResponseDTO> findAllByMemberId(Long memberId, Pageable pageable);
}