package base.application.post;

import base.domain.post.cache.CachedPost;
import base.domain.post.entity.Post;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class CachedPostProvider {

    public static final long max_read_count = 100;

    private static final String NAMESPACE = "posts";
    private static final String VERSION = "v1";

    private final Map<String, CachedPost> map = Collections.synchronizedMap(new HashMap<>());

    public CachedPost get(long postId) {
        CachedPost cachedPost = map.get(getKey(postId));
        log.debug("cache {}: {} => {}", (cachedPost != null) ? "hit" : "miss", getKey(postId), cachedPost);
        return cachedPost;
    }

    public void set(long postId, Post post) {
        CachedPost cachedPost = CachedPost.builder()
                .key(getKey(postId))
                .post(post)
                .build();

        map.put(getKey(postId), cachedPost);
        log.debug("cache update: {} => {}", getKey(postId), cachedPost);
    }

    public void remove(long postId){
        if(map.get(getKey(postId)) != null){
            map.remove(getKey(postId));
        }
    }

    private String getKey(long id) {
        return String.format("%s.%s.%d", NAMESPACE, VERSION, id);
    }
}
