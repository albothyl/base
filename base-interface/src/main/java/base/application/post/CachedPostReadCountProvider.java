package base.application.post;

import base.domain.post.PostRepository;
import base.domain.post.cache.CachedPostReadCount;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.ImmutableMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@Service
@RequiredArgsConstructor
public class CachedPostReadCountProvider {

    private final PostRepository postRepository;

    private static final long MAX_CACHE_SIZE = 1000000;
    private static final long MAX_READ_COUNT = 100;
    private static final long EXPIRE_DURATION = 10;

    private static final String NAMESPACE = "posts";
    private static final String VERSION = "v1";

    private LoadingCache<Long, CachedPostReadCount> loadingCache;

    {
        loadingCache = CacheBuilder.newBuilder()
                .maximumSize(MAX_CACHE_SIZE)
                .expireAfterAccess(EXPIRE_DURATION, TimeUnit.MINUTES)
                .build(new CacheLoader<Long, CachedPostReadCount>() {
                           @Override
                           public CachedPostReadCount load(Long id) throws Exception {
                               return postRepository.findById(id)
                                       .map(post -> CachedPostReadCount.builder()
                                               .count(new AtomicLong(post.getReadCount()))
                                               .key(getKey(id))
                                               .build())
                                       .orElse(null);
                           }
                       }
                );
    }

    public Optional<CachedPostReadCount> get(Long postId) {
        Optional<CachedPostReadCount> cachedReadCount = Optional.ofNullable(loadingCache.getUnchecked(postId));
        log.info("Cache {}: {} => {}", (cachedReadCount.isPresent()) ? "hit" : "miss", getKey(postId), cachedReadCount.get());
        return cachedReadCount;
    }
    public ImmutableMap<Long,CachedPostReadCount> getAll() {
        try {
            return loadingCache.getAll(loadingCache.asMap().keySet());
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isMaxReadCount(Long readCount) {
        return MAX_READ_COUNT <= readCount;
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
