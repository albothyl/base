package base.interfaces.post;

import base.application.post.PostService;
import base.domain.post.entity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    public Page<Post> findAllPosts(
            @PageableDefault(sort = {"postId"}) Pageable pageable) {
        return postService.findAllPosts(pageable);
    }

    @GetMapping(path = "/{postId}")
    public Post findPost(@PathVariable Long postId) {
        return postService.findByPostIdWithComments(postId);
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody @Valid Post post) {
        Post createPost = postService.createPost(post);
        return new ResponseEntity<>(createPost, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{postId}")
    public ResponseEntity<Post> updatePost(@PathVariable Long postId, @RequestBody @Valid Post post) {
        Post updatePost = postService.updatePost(postId, post);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @DeleteMapping(path = "/{postId}")
    public ResponseEntity<Post> deletePost(@PathVariable Long postId) {
        Post deletePost = postService.deletePost(postId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
