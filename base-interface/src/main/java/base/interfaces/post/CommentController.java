package base.interfaces.post;

import base.application.post.CommentService;
import base.domain.post.entity.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/posts/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<Comment> createComment(@RequestBody @Valid Comment comment) {
        Comment createComment = commentService.createComment(comment);
        return new ResponseEntity<>(createComment, HttpStatus.CREATED);
    }

    @PatchMapping(path = "/{commentId}")
    public ResponseEntity<Comment> updateComment(@PathVariable Long commentId, @RequestBody @Valid Comment comment) {
        Comment updateComment = commentService.updateComment(commentId, comment.getContents());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(path = "/{commentId}")
    public ResponseEntity<Comment> deleteComment(@PathVariable Long commentId) {
        Comment deleteComment = commentService.deleteComment(commentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
