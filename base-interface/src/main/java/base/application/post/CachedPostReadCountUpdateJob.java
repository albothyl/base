package base.application.post;

import base.domain.post.cache.CachedPostReadCount;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class CachedPostReadCountUpdateJob implements Job {

    private final PostManager postManager;
    private final CachedPostReadCountProvider provider;
    private int MINIMUM_CACHE_COUNT = 0;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        Map<Long, CachedPostReadCount> map = provider.getAll();
        map.entrySet().stream()
                .filter(this::moreThanZeroCount)
                .forEach(this::updateReadCount);

        provider.invalidateAll();

        log.info("cached post readCount scheduler update all <{}>", map.size());
    }

    private boolean moreThanZeroCount(Map.Entry<Long, CachedPostReadCount> entry) {
        return entry.getValue().getCount() > MINIMUM_CACHE_COUNT;
    }

    private void updateReadCount(Map.Entry<Long, CachedPostReadCount> entry) {
        postManager.updateReadCount(entry.getKey(), entry.getValue().getCount());
    }
}
