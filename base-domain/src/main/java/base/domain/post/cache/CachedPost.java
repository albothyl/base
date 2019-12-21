package base.domain.post.cache;

import base.domain.post.entity.Post;
import lombok.*;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CachedPost {
    private String key;
    public long count;
    private Post post;
}
