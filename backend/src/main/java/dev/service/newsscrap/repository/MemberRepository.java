package dev.service.newsscrap.repository;

import dev.service.newsscrap.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByNameAndPassword(String name, String password);
    Optional<Member> findByName(String name);
}