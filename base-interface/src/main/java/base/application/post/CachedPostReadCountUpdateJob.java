package base.application.post;

import base.domain.post.cache.CachedPostReadCount;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class CachedPostReadCountUpdateJob implements Job {

    private final PostManager postManager;
    private final CachedPostReadCountProvider provider;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        Map<Long, CachedPostReadCount> map = provider.getAll();
        map.entrySet().stream()
                .filter(entry -> provider.isMaxReadCount(entry.getValue().getCount()))
                .forEach(entry -> {
                    long postId = entry.getKey();
                    long readCount = entry.getValue().getCount();
                    postManager.updateReadCount(postId, readCount);
                });

        log.info("cached post readCount scheduler update all <{}>", map.size());
    }
}
