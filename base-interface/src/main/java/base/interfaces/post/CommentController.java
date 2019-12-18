package base.interfaces.post;

import base.application.post.CommentManager;
import base.domain.post.entity.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentManager commentManager;

    @PostMapping
    public ResponseEntity<Comment> createComment(@RequestBody Comment comment) {
        Comment createComment = commentManager.createComment(comment);
        return new ResponseEntity<>(createComment, HttpStatus.CREATED);
    }

    @PatchMapping(path = "/{commentId}")
    public ResponseEntity<Comment> updateComment(@PathVariable Long commentId, @RequestBody Comment comment) {
        commentManager.updateComment(commentId, comment.getContents());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(path = "/{commentId}")
    public ResponseEntity<Comment> deleteComment(@PathVariable Long commentId) {
        commentManager.deleteComment(commentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
