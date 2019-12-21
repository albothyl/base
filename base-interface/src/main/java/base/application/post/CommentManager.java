package base.application.post;

import base.domain.post.CommentRepository;
import base.domain.post.entity.Comment;
import base.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentManager {
    private final CommentRepository commentRepository;

    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public Comment updateComment(Long commentId, String contents) {
        Comment persistComment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("commentId " + commentId + " not found"));

        persistComment.update(contents);
        return persistComment;
    }

    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
