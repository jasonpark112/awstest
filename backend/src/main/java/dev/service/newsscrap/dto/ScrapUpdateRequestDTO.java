package dev.service.newsscrap.dto;

import dev.service.newsscrap.entity.Member;
import dev.service.newsscrap.entity.Scrap;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ScrapUpdateRequestDTO {
    private Long memberId;
    private String title;
    private String content;
    private String comment;

    public Scrap toEntity(Member member) {

        return Scrap.builder()
                .member(member)
                .title(title)
                .content(content)
                .comment(comment)
                .build();
    }
}
