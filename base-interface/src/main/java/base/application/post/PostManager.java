package base.application.post;

import base.domain.post.PostRepository;
import base.domain.post.entity.Post;
import base.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostManager {
    private final PostRepository postRepository;

    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    public Post updatePost(Long postId, Post post) {
        Post persistPost = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("PostId " + postId + " not found"));

        persistPost.update(post);
        return persistPost;
    }

    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }

    @Transactional(readOnly = true)
    public Page<Post> findAllPosts(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Post findByPostIdWithComments(Long postId) {
        return Optional.of(postRepository.findByIdWithComments(postId))
                .orElseThrow(() -> new ResourceNotFoundException("PostId " + postId + " not found"));
    }
}
