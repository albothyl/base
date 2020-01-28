package base.application.post;

import base.domain.post.cache.CachedPostReadCount;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class CachedPostReadCountUpdateJob implements Job {

    @Autowired
    private PostManager postManager;
    @Autowired
    private CachedPostReadCountProvider provider;

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
