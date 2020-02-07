package base.application.post

import base.domain.post.PostRepository
import base.domain.post.cache.CachedPostReadCount
import base.domain.post.entity.Post
import base.exception.ResourceNotFoundException
import io.github.benas.randombeans.EnhancedRandomBuilder
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import spock.lang.Specification

@ExtendWith(MockitoExtension.class)
class PostReadCountModifierTests extends Specification {

    def postRepository = Mock(PostRepository)
    def postReadCountModifier = new PostReadCountModifier(postRepository)

    def "PostReadCountModifier 조회수 1 증가 테스트"(){
        given:
        def readCount = 100
        def postId = 1l
        def cache = new CachedPostReadCount(postId)
        def post = Post.builder().postId(postId).readCount(readCount).build()
        def orgReadCount = post.getReadCount()

        when:
        cache.increaseCount()

        and:
        postReadCountModifier.appendReadCount(cache)

        then:
        1 * postRepository.findById(cache.getPostId()) >> Optional.ofNullable(post)

        and:
        post.getReadCount() == orgReadCount + 1
    }

    def "PostReadCountModifier ResourceNotFound 예외 테스트"(){
        given:
        def cache = EnhancedRandomBuilder.aNewEnhancedRandomBuilder().build().nextObject(CachedPostReadCount.class)

        when:
        postReadCountModifier.appendReadCount(cache)

        then:
        1 * postRepository.findById(cache.getPostId()) >> Optional.ofNullable(null)

        and:
        thrown(ResourceNotFoundException.class)
    }
}
