package base.application.post;

import base.domain.post.CommentRepository;
import base.domain.post.entity.Comment;
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

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CommentManagerTests {

    @InjectMocks
    private CommentManager commentManager;
    @Mock
    private CommentRepository commentRepository;
    private Comment comment;

    @BeforeEach
    public void setUp() {
        comment = EnhancedRandomBuilder.aNewEnhancedRandomBuilder().build()
                .nextObject(Comment.class);
    }

    @Test
    @DisplayName("commentManager 댓글 생성 테스트")
    public void commentCreateTest() {

        when(commentManager.createComment(comment)).thenReturn(comment);

        Comment createdComment = commentManager.createComment(comment);

        verify(commentRepository, atLeastOnce()).save(comment);
        assertThat(createdComment).isNotNull();
        assertThat(createdComment.getCommentId()).isEqualTo(comment.getCommentId());
    }

    @Test
    @DisplayName("commentManager 댓글 업데이트 테스트")
    public void commentUpdateTest() {
        String updateContents = "업데이트";

        given(commentRepository.findById(comment.getCommentId()))
                .willReturn(Optional.ofNullable(comment));

        Comment updateComment = commentManager.updateComment(comment.getCommentId(), updateContents);

        verify(commentRepository, atLeastOnce()).findById(comment.getCommentId());

        assertThat(updateComment).isNotNull();
        assertThat(updateComment.getContents()).isEqualTo(updateContents);

    }

    @Test
    @DisplayName("commentManager 댓글 삭제 테스트")
    public void commentDeleteTest() {

        commentManager.deleteComment(comment.getCommentId());

        verify(commentRepository, atLeastOnce()).deleteById(comment.getCommentId());
    }

    @Test
    @DisplayName("commentManager 댓글 업데이트 예외 테스트")
    public void commentUpdateExceptionTest() {
        String updateContents = "업데이트";

        Assertions.assertThrows(ResourceNotFoundException.class, () ->
                commentManager.updateComment(comment.getCommentId(), updateContents));

        verify(commentRepository, atLeastOnce()).findById(comment.getCommentId());
    }

}
