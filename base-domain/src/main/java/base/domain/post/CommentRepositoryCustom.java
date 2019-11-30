package base.domain.post;

import base.domain.post.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CommentRepositoryCustom {
    Page<Comment> findByPostId(Long postId, Pageable pageable);
    Optional<Comment> findByCommentIdAndPostId(Long id, Long postId);
}
