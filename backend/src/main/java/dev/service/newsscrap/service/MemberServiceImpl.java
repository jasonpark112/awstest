package dev.service.newsscrap.service;

import dev.service.newsscrap.entity.Member;
import dev.service.newsscrap.exception.DupulicationNameException;
import dev.service.newsscrap.exception.LoginFailedException;
import dev.service.newsscrap.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public Long save(Member member) {

        if(!validationName(member.getName())) {
            Member savedMember = memberRepository.save(member);
            return savedMember.getId();
        }

        throw new DupulicationNameException("중복된 아이디 입니다.");
    }

    @Override
    public Optional<Member> findById(Long id) {
        Optional<Member> member = memberRepository.findById(id);
        return member;
    }

    @Override
    public Long login(String name, String password) {
        Optional<Member> loginMember = memberRepository.findByNameAndPassword(name, password);

        if (loginMember.isEmpty()) {
            throw new LoginFailedException("아이디 또는 비밀번호가 일치하지 않습니다.");
        }

        return loginMember.get().getId();
    }

    /**
     * 중복이름 예외처리
     */
    private boolean validationName(String name) {
        Optional<Member> findMember = memberRepository.findByName(name);

        if (findMember.isPresent()) {
            return true;
        }
        return false;
    }
}
