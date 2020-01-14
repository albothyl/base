package base.application.post

import base.domain.post.PostRepository
import base.domain.post.cache.CachedPostReadCount
import base.domain.post.entity.Post
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import spock.lang.Specification

@ExtendWith(MockitoExtension.class)
class CachedReadCountProviderTests extends Specification {

    def postRepository = Mock(PostRepository)
    def cachedReadCountProvider = new CachedPostReadCountProvider(postRepository)

    def "cache get 테스트"() {
        given:
        def postId = 1
        def post = Post.builder()
                .postId(postId)
                .build()

        when:
        Optional<CachedPostReadCount> cachedReadCount = cachedReadCountProvider.get(postId)

        then:
        1 * postRepository.findById(postId) >> Optional.ofNullable(post)

        and:
        cachedReadCount.isPresent()
        cachedReadCount.get().getCount() == 0
    }

}

