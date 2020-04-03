package wind.yang.jojolduBook.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import wind.yang.jojolduBook.domain.posts.Posts;

import javax.persistence.Column;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity() {
        return Posts.builder()
                    .title(this.title)
                    .author(this.author)
                    .content(this.content)
                    .build();
    }
}
