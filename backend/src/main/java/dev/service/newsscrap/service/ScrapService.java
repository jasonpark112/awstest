package dev.service.newsscrap.service;

import dev.service.newsscrap.dto.ScrapRequestDTO;
import dev.service.newsscrap.entity.Scrap;

import java.util.List;

public interface ScrapService {

    Scrap findById(Long id);
    List<Scrap> findAll();

    Scrap save(ScrapRequestDTO scrapRequestDTO);
}
