package base.domain.post.cache;

import lombok.*;

import java.util.concurrent.atomic.AtomicLong;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CachedPostReadCount {
    private String key;
    private AtomicLong count;

    public Long increaseCount(){
        return count.incrementAndGet();
    }

    public Long getCount(){
        return count.get();
    }
}
