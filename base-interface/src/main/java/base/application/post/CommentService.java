package base.application.post;

import base.domain.post.CommentRepository;
import base.domain.post.entity.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    final CommentRepository commentRepository;

    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public boolean deleteComment(Long commentId) {
        Optional<Comment> comment = commentRepository.findById(commentId);
        if (comment.isPresent()) {
            commentRepository.delete(comment.get());
        }
        return comment.isPresent();
    }

    public Comment updateComment(Long commentId, Comment comment) {
        comment.setCommentId(commentId);
        return commentRepository.save(comment);
    }
}
