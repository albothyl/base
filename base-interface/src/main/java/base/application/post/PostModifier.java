package base.application.post;

import base.domain.post.PostRepository;
import base.domain.post.entity.Post;
import base.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostModifier {

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
}
