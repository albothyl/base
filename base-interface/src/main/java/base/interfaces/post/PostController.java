package base.interfaces.post;

import base.application.post.CachedPostReadCountUpdater;
import base.application.post.PostManager;
import base.domain.post.entity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostManager postManager;
    private final CachedPostReadCountUpdater updater;

    @GetMapping
    public Page<Post> findAllPosts(
            @PageableDefault(sort = {"postId"}, page = 10, size = 10) Pageable pageable) {
        return postManager.findAllPosts(pageable);
    }

    @GetMapping(path = "/{postId}")
    public Post findPost(@PathVariable Long postId) {
        Post post = postManager.findByPostIdWithComments(postId);
        updater.increase(post.getPostId());
        return post;
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        Post createPost = postManager.createPost(post);
        return new ResponseEntity<>(createPost, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{postId}")
    public ResponseEntity<Post> updatePost(@PathVariable Long postId, @RequestBody Post post) {
        postManager.updatePost(postId, post);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @DeleteMapping(path = "/{postId}")
    public ResponseEntity<Post> deletePost(@PathVariable Long postId) {
        postManager.deletePost(postId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
