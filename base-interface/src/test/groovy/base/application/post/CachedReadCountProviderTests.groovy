package base.application.post

import base.domain.post.PostRepository
import base.domain.post.cache.CachedPostReadCount
import base.domain.post.entity.Post
import com.google.common.collect.ImmutableMap
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import spock.lang.Specification

@ExtendWith(MockitoExtension.class)
class CachedReadCountProviderTests extends Specification {

    def postRepository = Mock(PostRepository)
    def cachedReadCountProvider = new CachedPostReadCountProvider(postRepository)

    def "increase 테스트"() {
        given:
        def postId = 1
        def post = Post.builder()
                .postId(postId)
                .build()

        when:
        Optional<CachedPostReadCount> cachedReadCount = cachedReadCountProvider.increaseCount(postId)

        then:
        1 * postRepository.findById(postId) >> Optional.ofNullable(post)

        and:
        cachedReadCount.isPresent()
        cachedReadCount.get().getCount() == 1
    }

    def "getAll 테스트"() {
        given:
        def postId = 1
        def post = Post.builder()
                .postId(postId)
                .build()

        when:
        Optional<CachedPostReadCount> cachedReadCount = cachedReadCountProvider.increaseCount(postId)
        ImmutableMap<Long, CachedPostReadCount> map = cachedReadCountProvider.getAll()

        then:
        1 * postRepository.findById(postId) >> Optional.ofNullable(post)

        and:
        cachedReadCount.isPresent()
        cachedReadCount.get().count == 1
        cachedReadCount.get().postId == postId
        map.size() == 1
    }

    def "invalidate 테스트"() {
        given:
        def postId = 1
        def post = Post.builder()
                .postId(postId)
                .build()

        when:
        cachedReadCountProvider.increaseCount(postId)
        cachedReadCountProvider.invalidate(postId)

        then:
        1 * postRepository.findById(postId) >> Optional.ofNullable(post)

        and:
        cachedReadCountProvider.getAll().size() == 0

    }
}

