package base.application.post;

import base.domain.post.PostRepository;
import base.domain.post.cache.CachedPostReadCount;
import base.domain.post.entity.Post;
import base.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PostReadCountModifier {

    private final PostRepository postRepository;

    public void appendReadCount(CachedPostReadCount cachedPostReadCount) {

        Long postId = cachedPostReadCount.getPostId();

        Post persistPost = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("PostId " + postId + " not found"));

        if (cachedPostReadCount.getCount() > 0) {
            persistPost.updateReadCount(cachedPostReadCount.getCount());
            log.debug("post({}) => update read count <{}>", postId, persistPost.getReadCount());
        }
    }
}
