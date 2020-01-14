package base.application.post;

import base.domain.post.cache.CachedPostReadCount;
import base.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CachedPostReadCountUpdater {
    private final CachedPostReadCountProvider cachedReadCountProvider;

    public void increase(Long postId) {
        CachedPostReadCount cachedReadCount = cachedReadCountProvider.get(postId)
                .orElseThrow(() -> new ResourceNotFoundException("cached readCount not found"));

        cachedReadCount.increaseCount();
    }
}
