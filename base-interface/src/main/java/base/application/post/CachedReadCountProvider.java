package base.application.post;

import base.domain.post.PostRepository;
import base.domain.post.cache.CachedReadCount;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CachedReadCountProvider {

    private final PostRepository postRepository;

    private static final long MAX_CACHE_SIZE = 1000000;
    private static final long MAX_READ_COUNT = 100;

    private static final String NAMESPACE = "posts";
    private static final String VERSION = "v1";

    private LoadingCache<Long, CachedReadCount> loadingCache;

    {
        loadingCache = CacheBuilder.newBuilder()
                .maximumSize(MAX_CACHE_SIZE)
                .build(new CacheLoader<Long, CachedReadCount>() {
                           @Override
                           public CachedReadCount load(Long id) throws Exception {
                               return postRepository.findById(id)
                                       .map(post -> CachedReadCount.builder()
                                               .count(post.getReadCount())
                                               .key(getKey(id))
                                               .build())
                                       .orElse(null);
                           }
                       }
                );
    }

    public Optional<CachedReadCount> get(Long postId) {
        Optional<CachedReadCount> cachedReadCount = Optional.ofNullable(loadingCache.getUnchecked(postId));
        log.info("Cache {}: {} => {}", (cachedReadCount.isPresent()) ? "hit" : "miss", getKey(postId), cachedReadCount.get());
        return cachedReadCount;
    }

    public boolean isMaxReadCount(Long postId) {
        Optional<CachedReadCount> cachedReadCount = get(postId);
        return cachedReadCount.isPresent() ? cachedReadCount.get().getCount() == MAX_READ_COUNT : false;
    }

    public void remove(Long postId) {
        loadingCache.invalidate(postId);
        log.debug("Cache invalidate key=<{}>, size=<{}>", getKey(postId), loadingCache.size());
    }

    public void refresh(Long postId) {
        loadingCache.refresh(postId);
        log.debug("Cache refresh key=<{}>, size=<{}>", getKey(postId), loadingCache.size());
    }

    public long getSize() {
        return loadingCache.size();
    }

    private String getKey(long id) {
        return String.format("%s.%s.%d", NAMESPACE, VERSION, id);
    }
}
