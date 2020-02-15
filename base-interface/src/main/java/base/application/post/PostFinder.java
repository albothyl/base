package base.application.post;

import base.domain.post.PostRepository;
import base.domain.post.entity.Post;
import base.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostFinder {

    private final PostRepository postRepository;

    public Page<Post> findAllPosts(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    public Post findByPostId(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("PostId " + postId + " not found"));
    }
}
