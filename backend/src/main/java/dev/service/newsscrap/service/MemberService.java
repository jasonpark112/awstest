package dev.service.newsscrap.service;

import dev.service.newsscrap.entity.Member;

import java.util.Optional;

public interface MemberService {
    Long save(Member member);

    Optional<Member> findById(Long id);

    Long login(String name, String password);
}
