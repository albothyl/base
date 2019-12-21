package base.application.post;

import base.domain.post.CommentRepository;
import base.domain.post.PostRepository;
import base.domain.post.entity.Comment;
import base.domain.post.entity.Post;
import base.exception.ResourceNotFoundException;
import io.github.benas.randombeans.EnhancedRandomBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PostManagerTests {
    @InjectMocks
    private PostManager postManager;
    @Mock
    private PostRepository postRepository;

    private Post post;

    @BeforeEach
    public void setUp() {
        post = EnhancedRandomBuilder.aNewEnhancedRandomBuilder().build()
                .nextObject(Post.class);
    }

    @Test
    @DisplayName("postManager 게시글 생성 테스트")
    public void postCreateTest() {
        when(postManager.createPost(post)).thenReturn(post);

        Post createdPost = postManager.createPost(post);

        verify(postRepository, atLeastOnce()).save(post);
        assertThat(createdPost).isNotNull();
        assertThat(createdPost.getPostId()).isEqualTo(post.getPostId());
    }


    @Test
    @DisplayName("postManager 게시글 업데이트 테스트")
    public void postUpdateTest() {
        Post updatePost = EnhancedRandomBuilder.aNewEnhancedRandomBuilder().build()
                .nextObject(Post.class);

        given(postRepository.findById(post.getPostId()))
                .willReturn(Optional.ofNullable(updatePost));

        Post updatePosted = postManager.updatePost(post.getPostId(), post);

        verify(postRepository, atLeastOnce()).findById(post.getPostId());

        assertThat(updatePosted).isNotNull();
        assertThat(updatePosted).isEqualTo(updatePost);

    }

    @Test
    @DisplayName("postManager 게시글 삭제 테스트")
    public void postDeleteTest() {
        postManager.deletePost(post.getPostId());
        verify(postRepository, atLeastOnce()).deleteById(post.getPostId());
    }

    @Test
    @DisplayName("postManager 게시글 모두 조회 테스트")
    public void postFindAllTest() {
        postManager.findAllPosts(any());
        verify(postRepository, atLeastOnce()).findAll((Pageable)any());
    }

    @Test
    @DisplayName("postManager 게시글 번호 조회 테스트")
    public void postFindByPostIdWithCommentsTest() {

        given(postRepository.findByIdWithComments(post.getPostId()))
                .willReturn((post));

        Post findPost = postManager.findByPostIdWithComments(post.getPostId());

        verify(postRepository, atLeastOnce()).findByIdWithComments(post.getPostId());
        assertThat(findPost).isNotNull();
        assertThat(findPost).isEqualTo(post);
        assertThat(findPost.getPostId()).isEqualTo(post.getPostId());
    }

    @Test
    @DisplayName("postManager 게시글 update 예외 테스트")
    public void postUpdateExceptionTest() {

        Post updatePost = EnhancedRandomBuilder.aNewEnhancedRandomBuilder().build()
                .nextObject(Post.class);

        Assertions.assertThrows(ResourceNotFoundException.class, () ->
            postManager.updatePost(post.getPostId(), updatePost));

        verify(postRepository, atLeastOnce()).findById(post.getPostId());
    }

    @Test
    @DisplayName("postManager 게시글 조회 예외 테스트")
    public void postFindExceptionTest() {
        Assertions.assertThrows(ResourceNotFoundException.class, () ->
            postManager.findByPostIdWithComments(post.getPostId()));

        verify(postRepository, atLeastOnce()).findByIdWithComments(post.getPostId());
    }

    @Test
    @DisplayName("조회수 업데이트 테스트")
    public void readCountUpdateTest() {
        Post updatePost = Post.builder()
                .postId(1l)
                .contents("내용")
                .build();

        long expectedReadCount = 10;

        given(postRepository.findById(post.getPostId()))
                .willReturn(Optional.ofNullable(updatePost));

        Post updatePosted = postManager.updateReadCount(post.getPostId(), expectedReadCount);

        verify(postRepository, atLeastOnce()).findById(post.getPostId());

        assertThat(updatePosted).isNotNull();
        assertThat(updatePosted.getReadCount()).isEqualTo(expectedReadCount);

    }
}
