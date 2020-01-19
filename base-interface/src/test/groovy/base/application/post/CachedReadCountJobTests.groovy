package base.application.post

import base.domain.post.cache.CachedPostReadCount
import com.google.common.collect.ImmutableMap
import org.junit.Ignore
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

    @Ignore
    def "stream foreach 성능 테스트 비교"() {
        given:
        def postId = 1l
        def count = 1000000
        def cache = new CachedPostReadCount("")
        def builder = ImmutableMap.builder()

        (1..100).each {
            cache.increaseCount()
        }
        (1..count).each {
            builder.put(postId++, cache)
        }

        def map = builder.build()

        when:
        long start = System.currentTimeMillis()
        job.execute(jobExecutionContext)
        long streamResult = System.currentTimeMillis() - start

        long start2 = System.currentTimeMillis()
        job.executeWithNotStream(jobExecutionContext)
        long notStreamResult = System.currentTimeMillis() - start2

        then:
        2 * cachedReadCountProvider.getAll() >> map

        and:
        printf "stream foreach result " + streamResult + ", not stream foreach result " + notStreamResult

    }
}








