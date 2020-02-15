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

@Slf4j
@Service
@RequiredArgsConstructor
public class CachedPostReadCountProvider {

    private final PostRepository postRepository;

    private static final long MAX_CACHE_SIZE = 1000000;
    private static final long EXPIRE_DURATION = 10;

    LoadingCache<Long, CachedPostReadCount> loadingCache = CacheBuilder.newBuilder()
            .maximumSize(MAX_CACHE_SIZE)
            .expireAfterWrite(EXPIRE_DURATION, TimeUnit.MINUTES)
            .build(createCacheLoader());

    public Optional<CachedPostReadCount> increaseCount(Long postId) {
        Optional<CachedPostReadCount> cachedReadCount = Optional.ofNullable(loadingCache.getUnchecked(postId));
        log.info("Cache {}: {} => {}", (cachedReadCount.isPresent()) ? "hit" : "miss", postId, cachedReadCount.get());

        if (cachedReadCount.isPresent()) {
            cachedReadCount.get().increaseCount();
        }
        return cachedReadCount;
    }

    public ImmutableMap<Long, CachedPostReadCount> getAll() {
        try {
            return loadingCache.getAll(loadingCache.asMap().keySet());
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public void invalidate(Long postId) {
        loadingCache.invalidate(postId);
    }

    private CacheLoader<Long, CachedPostReadCount> createCacheLoader() {
        return new CacheLoader<Long, CachedPostReadCount>() {
            @Override
            public CachedPostReadCount load(Long postId) {
                return postRepository.findById(postId)
                        .map(post -> new CachedPostReadCount(postId))
                        .orElse(null);
            }
        };
    }
}
