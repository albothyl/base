package base.application.post

import base.domain.post.cache.CachedPostReadCount
import com.google.common.collect.ImmutableMap
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import spock.lang.Specification

@ExtendWith(MockitoExtension.class)
class CachedReadCountSchedulerTests extends Specification {

    def postReadCountModifier = Mock(PostReadCountModifier)
    def cachedReadCountProvider = Mock(CachedPostReadCountProvider)
    def postReadCountScheduler = new PostReadCountScheduler(cachedReadCountProvider, postReadCountModifier)

    def "ReadCountScheduler append 태스크 테스트"() {
        given:
        def postId = 1l
        def cache = new CachedPostReadCount(postId)

        def map = ImmutableMap.builder()
                .put(postId, cache)
                .build()

        when:
        (1..100).each {
            cache.increaseCount()
        }
        and:
        postReadCountScheduler.appendCachedReadCount()

        then:
        1 * cachedReadCountProvider.getAll() >> map
        1 * postReadCountModifier.appendReadCount(cache)
    }
}
