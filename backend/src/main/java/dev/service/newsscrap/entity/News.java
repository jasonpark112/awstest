package dev.service.newsscrap.entity;

import dev.service.newsscrap.dto.NewsRequest;
import lombok.*;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.lyncode.jtwig.functions.util.HtmlUtils.stripTags;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String originallink;

    @Column(nullable = false)
    private String link;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private LocalDateTime pubDate;

    @Column(nullable = false)
    private String keyword;


    public static News from(NewsRequest newsRequest) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime pubDate = LocalDateTime.parse(newsRequest.getPubDate(), formatter);

        return News.builder()
                .title(stripTags(newsRequest.getTitle()))
                .link(newsRequest.getLink())
                .originallink(newsRequest.getOriginallink())
                .description(stripTags(newsRequest.getDescription()))
                .pubDate(pubDate)
                .keyword(newsRequest.getKeyword())
                .build();
    }

    public static List<News> fromJsonArray(JSONArray jsonArray, String keyword) {
        List<News> newsList = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.RFC_1123_DATE_TIME.withLocale(Locale.ENGLISH);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject json = jsonArray.getJSONObject(i);

            ZonedDateTime zonedDateTime = ZonedDateTime.parse(json.getString("pubDate"), formatter);
            LocalDateTime pubDate = zonedDateTime.toLocalDateTime();

            newsList.add(News.builder()
                    .title(stripTags(json.getString("title")))
                    .link(json.getString("link"))
                    .originallink(json.getString("originallink"))
                    .description(stripTags(json.getString("description")))
                    .pubDate(pubDate)
                    .keyword(keyword)
                    .build()
            );
        }

        return newsList;
    }
}