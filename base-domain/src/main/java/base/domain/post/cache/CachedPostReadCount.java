package base.domain.post.cache;

import lombok.*;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CachedPostReadCount {
    private String key;
    private long count;

    public void increaseCount(){
        this.count += 1;
    }
}
