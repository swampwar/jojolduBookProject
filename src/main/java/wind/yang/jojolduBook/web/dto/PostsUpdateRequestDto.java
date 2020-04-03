package wind.yang.jojolduBook.web.dto;

import lombok.Builder;
import lombok.Getter;
import wind.yang.jojolduBook.domain.posts.Posts;

@Getter
public class PostsUpdateRequestDto {
    private String title;
    private String content;
    private String author;

    @Builder
    public PostsUpdateRequestDto(String title, String content, String author) {
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
