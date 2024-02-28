package dev.service.newsscrap.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Scrap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "news_id")
    private News news;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    private String comment;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdTime;

    @Column(nullable = false)
    @LastModifiedDate
    private LocalDateTime updatedTime;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Scrap(Long id, News news, String title, String content, String comment, LocalDateTime createdTime, LocalDateTime updatedTime, Member member) {
        this.id = id;
        this.news = news;
        this.title = title;
        this.content = content;
        this.comment = comment;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
        this.member = member;
    }

    public void updateScrap(String title, String content, String comment, LocalDateTime updatedTime) {
        this.title = title;
        this.content = content;
        this.comment = comment;
        this.updatedTime = updatedTime;
    }
}