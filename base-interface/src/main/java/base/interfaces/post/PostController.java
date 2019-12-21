package base.interfaces.post;

import base.application.post.CachedPostProvider;
import base.application.post.PostManager;
import base.domain.post.cache.CachedPost;
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

    private final PostManager postManager;
    private final CachedPostProvider cachedPostProvider;

    @GetMapping
    public Page<Post> findAllPosts(
            @PageableDefault(sort = {"postId"}, page = 10, size = 10) Pageable pageable) {
        Page<Post> posts = postManager.findAllPosts(pageable);
        posts.forEach(this::updateReadCount);
        return posts;
    }

    @GetMapping(path = "/{postId}")
    public Post findPost(@PathVariable Long postId) {
        Post post = postManager.findByPostIdWithComments(postId);
        updateReadCount(post);
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
        cachedPostProvider.remove(postId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void updateReadCount(Post post){
        CachedPost cachedPost = cachedPostProvider.get(post.getPostId());
        if(cachedPost==null){
            cachedPostProvider.set(post.getPostId(), post);
        } else {
            cachedPost.count++;
            if(cachedPost.getCount() == cachedPostProvider.max_read_count){
                postManager.updateReadCount(cachedPost.getPost().getPostId(), cachedPost.getCount());
                cachedPost.count=0;
            }
        }
    }
}
