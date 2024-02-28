package dev.service.newsscrap.repository;

import dev.service.newsscrap.entity.Scrap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScrapRepository extends JpaRepository<Scrap, Long> {
    Page<Scrap> findAllByMemberIdOrderByUpdatedTimeDesc(Long memberId, Pageable pageable);
}
