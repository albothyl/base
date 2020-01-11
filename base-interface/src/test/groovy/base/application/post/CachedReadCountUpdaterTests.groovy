package base.application.post


import base.domain.post.cache.CachedPostReadCount
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import spock.lang.Specification

import java.util.concurrent.atomic.AtomicLong

@ExtendWith(MockitoExtension.class)
class CachedReadCountUpdaterTests extends Specification {

    def cachedReadCountProvider = Mock(CachedPostReadCountProvider)
    def postManager = Mock(PostManager)

    def cachedPostReadCountUpdater = new CachedPostReadCountUpdater(cachedReadCountProvider, postManager)


    def "update test"() {
        given:
        def postId = 1
        def readCount = 100
        def cachedReadCount = CachedPostReadCount.builder()
                .count(new AtomicLong(readCount))
                .build()

        when:
        cachedPostReadCountUpdater.update(postId)

        then:
        1 * cachedReadCountProvider.get(postId) >> Optional.ofNullable(cachedReadCount)
        1 * cachedReadCountProvider.isMaxReadCount(++readCount) >> true

        and:
        1 * postManager.updateReadCount(postId, readCount)
        1 * cachedReadCountProvider.refresh(postId)

    }

}
