package base.application.post;

import base.domain.post.PostRepository;
import base.domain.post.entity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    final PostRepository postRepository;

    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    public Post updatePost(Long postId, Post post) {
        post.setPostId(postId);
        return postRepository.save(post);
    }

    public boolean deletePost(Long postId) {
        Optional<Post> post = postRepository.findById(postId);
        if (post.isPresent()) {
            postRepository.delete(post.get());
        }
        return post.isPresent();
    }

    public Optional<Post> findPost(Long postId) {
        return postRepository.findById(postId);
    }

    public Page<Post> findAllPost(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    public List<Post> findByIdWithComments(Long postId) {
        return postRepository.findByIdWithComments(postId);
    }
}
