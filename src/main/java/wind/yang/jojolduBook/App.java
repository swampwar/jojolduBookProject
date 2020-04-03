package wind.yang.jojolduBook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import wind.yang.jojolduBook.domain.posts.Posts;

@EnableJpaAuditing // JPA Auditing 활성화
@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
