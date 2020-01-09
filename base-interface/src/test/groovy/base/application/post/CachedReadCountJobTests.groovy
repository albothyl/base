package base.application.post

import base.domain.post.cache.CachedPostReadCount
import com.google.common.collect.ImmutableMap
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.quartz.JobExecutionContext
import spock.lang.Specification

import java.util.concurrent.atomic.AtomicLong

@ExtendWith(MockitoExtension.class)
class CachedReadCountJobTests extends Specification {

    def postManager = Mock(PostManager)
    def cachedReadCountProvider = Mock(CachedPostReadCountProvider)
    def job = new CachedPostReadCountUpdateJob(postManager, cachedReadCountProvider)
    def jobExecutionContext = Stub(JobExecutionContext)

    def "execute job test"() {
        given:
        def postId = 1l
        def readCount = 100
        def cache = CachedPostReadCount.builder()
                .count(new AtomicLong(readCount))
                .build()
        def map = ImmutableMap.builder()
                .put(postId, cache)
                .build()

        when:
        job.execute(jobExecutionContext)

        then:
        1 * cachedReadCountProvider.getAll() >> map
        1 * postManager.updateReadCount(postId, readCount)
    }
}
