package base.interfaces.post;

import base.application.post.CommentModifier;
import base.domain.post.entity.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentModifier commentModifier;

    @PostMapping
    public Comment createComment(@RequestBody Comment comment) {
        return commentModifier.createComment(comment);
    }

    @PatchMapping(path = "/{commentId}")
    public ResponseEntity<Comment> updateComment(@PathVariable Long commentId, @RequestBody Comment comment) {
        commentModifier.updateComment(commentId, comment.getContents());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(path = "/{commentId}")
    public ResponseEntity<Comment> deleteComment(@PathVariable Long commentId) {
        commentModifier.deleteComment(commentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
