package wind.yang.jojolduBook.service.posts;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wind.yang.jojolduBook.domain.posts.Posts;
import wind.yang.jojolduBook.domain.posts.PostsRepository;
import wind.yang.jojolduBook.web.dto.PostsListResponseDto;
import wind.yang.jojolduBook.web.dto.PostsResponseDto;
import wind.yang.jojolduBook.web.dto.PostsSaveRequestDto;
import wind.yang.jojolduBook.web.dto.PostsUpdateRequestDto;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다! id = " + id));

        // 위의 DB조회 시점에 영속성 컨텍스트에 포함되며 객체의 값만 변경해도 트랜잭션이 끝나는 시점에 DB에 반영된다.(Dirty Checking)
        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다! id = " + id));

        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc(){
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다! id = " + id));

        postsRepository.deleteById(id);
    }
}
