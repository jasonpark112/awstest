package dev.service.newsscrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
@EnableJpaAuditing
public class NewsscrapApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewsscrapApplication.class, args);
	}

}
