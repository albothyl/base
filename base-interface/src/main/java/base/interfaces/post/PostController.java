package base.interfaces.post;

import base.application.post.CachedPostReadCountProvider;
import base.application.post.PostManager;
import base.domain.post.cache.CachedPostReadCount;
import base.domain.post.entity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostManager postManager;
    private final CachedPostReadCountProvider cachedReadCountProvider;

    @GetMapping
    public Page<Post> findAllPosts(
            @PageableDefault(sort = {"postId"}, page = 10, size = 10) Pageable pageable) {
        Page<Post> posts = postManager.findAllPosts(pageable);
        return posts;
    }

    @GetMapping(path = "/{postId}")
    public Post findPost(@PathVariable Long postId) {
        Post post = postManager.findByPostIdWithComments(postId);

        Optional<CachedPostReadCount> cachedReadCount = cachedReadCountProvider.get(postId);
        long count = cachedReadCount.get().increaseCount();

        if(cachedReadCountProvider.isMaxReadCount(count)){
            postManager.updateReadCount(postId, count);
            cachedReadCountProvider.refresh(postId);
        }
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
        cachedReadCountProvider.remove(postId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
