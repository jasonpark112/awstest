package dev.service.newsscrap.service;

import dev.service.newsscrap.dto.CustomScrapResponseDTO;
import dev.service.newsscrap.dto.ScrapRequest;
import dev.service.newsscrap.entity.Member;
import dev.service.newsscrap.entity.News;
import dev.service.newsscrap.entity.Scrap;
import dev.service.newsscrap.exception.InvalidMemberException;
import dev.service.newsscrap.repository.MemberRepository;
import dev.service.newsscrap.repository.NewsRepository;
import dev.service.newsscrap.repository.ScrapRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScrapServiceImpl implements ScrapService {

    private final ScrapRepository scrapRepository;
    private final MemberRepository memberRepository;
    private final NewsRepository newsRepository;

    @Override
    public Scrap findById(Long id) {
        Optional<Scrap> scrapOptional = scrapRepository.findById(id);

        return scrapOptional.orElse(null);
    }

    @Override
    public Scrap save(ScrapRequest scrapRequest) {

        Optional<Member> memberOptional = memberRepository.findById(scrapRequest.getMemberId());
        Optional<News> newsOptional = newsRepository.findById(scrapRequest.getNewsId());

        if (memberOptional.isPresent()) {
            Member member = memberOptional.get();
            News news = newsOptional.get();

            Scrap scrap = scrapRequest.toEntity(member, news);

            return scrapRepository.save(scrap);

        } else {
            throw new RuntimeException("ID가 " + scrapRequest.getMemberId() + "인 사용자를 찾을 수 없습니다.");
        }
    }

    public List<Scrap> findAll() {
        List<Scrap> scraps = new ArrayList<>();
        scrapRepository.findAll().forEach(scraps::add);

        return scraps;
    }

    @Transactional
    @Override
    public Scrap update(Long id, Scrap updateScrap) {
        // 기존 scrap
        Optional<Scrap> findScrap = scrapRepository.findById(id);

        if (findScrap.get().getMember().getId() != updateScrap.getMember().getId()) {
            throw new InvalidMemberException("수정할 수 없는 회원입니다.");
        }

        Scrap exScrap = findScrap.get();
        findScrap.get().updateScrap(
                updateScrap.getTitle() != null ? updateScrap.getTitle() : exScrap.getTitle(),
                updateScrap.getContent() != null ? updateScrap.getContent() : exScrap.getContent(),
                updateScrap.getComment() != null ? updateScrap.getComment() : exScrap.getComment(),
                updateScrap.getUpdatedTime() != null ? updateScrap.getUpdatedTime() : exScrap.getUpdatedTime()
        );

        return exScrap;
    }

    @Override
    public void deleteById(Long scrapId, Long memberId) {

        if (findById(scrapId).getMember().getId() == memberId) {
            scrapRepository.deleteById(scrapId);

        } else {
            throw new RuntimeException("Error Occurred.");
        }
    }

    @Override
    public Page<CustomScrapResponseDTO> findAllByMemberId(Long memberId, Pageable pageable) {

        Page<Scrap> scraps = scrapRepository.findAllByMemberIdOrderByUpdatedTimeDesc(memberId, pageable);

        return scraps.map(CustomScrapResponseDTO::from);
    }
}
