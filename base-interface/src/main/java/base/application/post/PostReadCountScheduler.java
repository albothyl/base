package base.application.post;

import base.domain.post.cache.CachedPostReadCount;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostReadCountScheduler {

    private final CachedPostReadCountProvider postReadCountProvider;
    private final PostReadCountModifier postReadCountModifier;

    @Scheduled(cron = "0 0 1/0 * * ?")
    public void appendCachedReadCount() {
        postReadCountProvider.getAll().entrySet().stream()
                .forEach(readCountEntry -> {
                    Long postId = readCountEntry.getKey();
                    CachedPostReadCount cachedPostReadCount = readCountEntry.getValue();
                    postReadCountModifier.appendReadCount(cachedPostReadCount);
                    postReadCountProvider.invalidate(postId);
                });


    }
}
