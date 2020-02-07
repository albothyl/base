package base.interfaces.post;

import base.application.post.CachedPostReadCountProvider;
import base.application.post.PostFinder;
import base.application.post.PostModifier;
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

    private final PostFinder postFinder;
    private final PostModifier postModifier;
    private final CachedPostReadCountProvider cachedReadCountProvider;

    @GetMapping
    public Page<Post> findAllPosts(
            @PageableDefault(sort = {"postId"}, page = 10, size = 10) Pageable pageable) {
        return postFinder.findAllPosts(pageable);
    }

    @GetMapping(path = "/{postId}")
    public Post findPost(@PathVariable Long postId) {
        Post post = postFinder.findByPostId(postId);
        cachedReadCountProvider.increaseCount(postId);
        return post;
    }

    @PostMapping
    public Post createPost(@RequestBody Post post) {
        return postModifier.createPost(post);
    }

    @PutMapping(path = "/{postId}")
    public ResponseEntity<Post> updatePost(@PathVariable Long postId, @RequestBody Post post) {
        postModifier.updatePost(postId, post);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @DeleteMapping(path = "/{postId}")
    public ResponseEntity<Post> deletePost(@PathVariable Long postId) {
        postModifier.deletePost(postId);
        cachedReadCountProvider.invalidate(postId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
