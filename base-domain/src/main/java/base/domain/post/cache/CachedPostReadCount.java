package base.domain.post.cache;

import lombok.*;

import java.util.concurrent.atomic.AtomicLong;

@ToString
public class CachedPostReadCount {
    private Long postId;
    private AtomicLong count = new AtomicLong(0);

    public CachedPostReadCount(Long postId) {
        this.postId = postId;
    }

    public Long increaseCount() {
        return count.incrementAndGet();
    }

    public Long getPostId() {
        return postId;
    }

    public Long getCount() {
        return count.get();
    }
}
