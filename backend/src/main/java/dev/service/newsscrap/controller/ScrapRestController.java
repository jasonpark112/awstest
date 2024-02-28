package dev.service.newsscrap.controller;

import dev.service.newsscrap.dto.CustomScrapResponseDTO;
import dev.service.newsscrap.dto.ScrapRequest;
import dev.service.newsscrap.dto.ScrapResponse;
import dev.service.newsscrap.dto.ScrapUpdateRequestDTO;
import dev.service.newsscrap.dto.ScrapUpdateResponseDTO;
import dev.service.newsscrap.entity.Member;
import dev.service.newsscrap.entity.Scrap;
import dev.service.newsscrap.exception.InvalidMemberException;
import dev.service.newsscrap.exception.NotExistDataException;
import dev.service.newsscrap.service.MemberService;
import dev.service.newsscrap.service.NewsService;
import dev.service.newsscrap.service.ScrapService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/scrap")
@RestController
@RequiredArgsConstructor
public class ScrapRestController {

    private final ScrapService scrapService;
    private final MemberService memberService;
    private final NewsService newsService;

    @PostMapping("/create")
    public void addScrap(@RequestBody ScrapRequest scrapRequest) {

        scrapService.save(scrapRequest);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
        Scrap scrap = scrapService.findById(id);

        return ResponseEntity.ok(ScrapResponse.toDTO(scrap));
        } catch (NotExistDataException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /**
     * Scrap 수정
     */
    @PatchMapping("/update/{id}")
    public ResponseEntity<?> updateScrap(@PathVariable Long id, @RequestBody ScrapUpdateRequestDTO scrapupdateRequestDTO) {
        Member member = memberService.findById(scrapupdateRequestDTO.getMemberId()).get();
        Scrap scrap = scrapupdateRequestDTO.toEntity(member);

        try {
            Scrap updatedScrap = scrapService.update(id, scrap);
            ScrapUpdateResponseDTO scrapResponseDTO = ScrapUpdateResponseDTO.toDTO(updatedScrap);

            return ResponseEntity.ok(scrapResponseDTO);
        } catch (NotExistDataException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    @PostMapping("/delete")
    public void deleteScrap(@RequestParam Long scrapId, Long memberId) {

        scrapService.deleteById(scrapId, memberId);
    }

    // 알아야 할 값:
    // totalPosts(모든 글 개수), currentPage(현재 페이지 번호), postsPerPage(한 페이지당 표시할 글 수), displayPageNum(한번에 표시할 페이지 수)
    // 구해야 할 값:
    // prev(1만 false bec: 1이 시작), next(end 페이지인 경우만 false), startPage, endPage
    @GetMapping("/{id}/custom-list")
    public Page<CustomScrapResponseDTO> findCustomList(@PathVariable Long id, Pageable pageable) {
        return scrapService.findAllByMemberId(id, pageable);
    }
}