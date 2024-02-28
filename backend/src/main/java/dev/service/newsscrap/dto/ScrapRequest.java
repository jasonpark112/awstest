package dev.service.newsscrap.dto;

import dev.service.newsscrap.entity.Member;
import dev.service.newsscrap.entity.News;
import dev.service.newsscrap.entity.Scrap;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ScrapRequest {
    private String title;
    private String content;
    private String comment;

    private Long memberId;
    private Long newsId;

    public ScrapRequest(String title, String content, String comment, Long memberId, Long newsId) {
        this.title = title;
        this.content = content;
        this.comment = comment;

        this.memberId = memberId;
        this.newsId = newsId;
    }

    public Scrap toEntity(Member member, News news) {
        return  Scrap.builder()
                .title(title)
                .content(content)
                .comment(comment)
                .member(member)
                .news(news)
                .build();
    }
}