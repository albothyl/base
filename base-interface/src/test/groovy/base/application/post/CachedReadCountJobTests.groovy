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

    def "execute job 호출 시 updateReadCount  검증 테스트"() {
        given:
        def postId = 1l
        def readCount = 100
        def cache = new CachedPostReadCount("")

        def map = ImmutableMap.builder()
                .put(postId, cache)
                .build()

        when:
        (1..readCount).each {
            cache.increaseCount()
        }
        job.execute(jobExecutionContext)

        then:
        1 * cachedReadCountProvider.getAll() >> map
        1 * postManager.updateReadCount(postId, readCount)
        1 * cachedReadCountProvider.invalidateAll()
    }
}
