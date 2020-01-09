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
                .filter(this::moreThanZeroCount)
                .forEach(this::updateReadCount);

        log.info("cached post readCount scheduler update all <{}>", map.size());
    }

    private boolean moreThanZeroCount(Map.Entry<Long, CachedPostReadCount> entry){
        return entry.getValue().getCount() > 0;
    }

    private void updateReadCount(Map.Entry<Long, CachedPostReadCount> entry){
        try{
            postManager.updateReadCount(entry.getKey(), entry.getValue().getCount());
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
