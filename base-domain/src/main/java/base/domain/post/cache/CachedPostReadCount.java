package base.domain.post.cache;

import lombok.*;

import java.util.concurrent.atomic.AtomicLong;

@ToString
public class CachedPostReadCount {
    private String key;
    private AtomicLong count = new AtomicLong(0);

    public CachedPostReadCount(String key){
        this.key = key;
    }

    public Long increaseCount(){
        return count.incrementAndGet();
    }

    public Long getCount(){
        return count.get();
    }
}
