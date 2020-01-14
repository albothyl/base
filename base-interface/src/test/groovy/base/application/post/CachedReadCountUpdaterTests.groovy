package base.application.post


import base.domain.post.cache.CachedPostReadCount
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import spock.lang.Specification


@ExtendWith(MockitoExtension.class)
class CachedReadCountUpdaterTests extends Specification {

    def cachedReadCountProvider = Mock(CachedPostReadCountProvider)

    def cachedPostReadCountUpdater = new CachedPostReadCountUpdater(cachedReadCountProvider)


    def "increase test"() {
        given:
        def postId = 1
        def cachedReadCount = new CachedPostReadCount("")

        when:
        cachedPostReadCountUpdater.increase(postId)

        then:
        1 * cachedReadCountProvider.get(postId) >> Optional.ofNullable(cachedReadCount)
    }

}
