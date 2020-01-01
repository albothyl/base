package base.application.post

import base.domain.post.PostRepository
import base.domain.post.cache.CachedReadCount
import base.domain.post.entity.Post
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import spock.lang.Specification

@ExtendWith(MockitoExtension.class)
class CachedReadCountProviderTests extends Specification {

    private CachedReadCountProvider cachedReadCountProvider;
    private PostRepository postRepository;

    def setup(){
        postRepository = Stub(PostRepository)
        cachedReadCountProvider = new CachedReadCountProvider(postRepository)
    }

    def "cache get 테스트"() {
        given:
        Long postId = 1l
        Post post = Post.builder()
                .postId(postId)
                .build()

        when:
        Optional<CachedReadCount> cachedReadCount = cachedReadCountProvider.get(postId)

        then:
        postRepository.findById(postId) >> Optional.ofNullable(post)
        cachedReadCount.isPresent()
        cachedReadCount.get().getCount() == 0
    }


    def "cache isMaxReadCount 테스트"() {
        given:
        Long postId = 1l
        Post post = Post.builder()
                .postId(postId)
                .build()

        when:
        boolean firstGetIsMax = cachedReadCountProvider.isMaxReadCount(postId)
        Optional<CachedReadCount> cachedReadCount = cachedReadCountProvider.get(postId)

        (1..100).each {
            cachedReadCount.get().increaseCount()
        }

        boolean secondGetIsMax = cachedReadCountProvider.isMaxReadCount(postId)

        then:
        postRepository.findById(postId) >> Optional.ofNullable(post)
        firstGetIsMax == false
        secondGetIsMax == true
    }

    def "cache refresh 테스트"() {
        given:
        Long postId = 1l
        Post post = Post.builder()
                .postId(postId)
                .build()

        when:
        Optional<CachedReadCount> cachedReadCount = cachedReadCountProvider.get(postId)
        cachedReadCount.get().increaseCount()
        cachedReadCountProvider.refresh(postId)
        Optional<CachedReadCount> cachedReadCount2 = cachedReadCountProvider.get(postId)


        then:
        postRepository.findById(postId) >> Optional.ofNullable(post)
        cachedReadCount.get().getCount() == 1
        cachedReadCount2.get().getCount() == 0
    }

    def "cache remove 테스트"() {
        given:
        Long postId = 1l
        Post post = Post.builder()
                .postId(postId)
                .build()

        when:
        cachedReadCountProvider.get(postId)
        cachedReadCountProvider.remove(postId)

        then:
        postRepository.findById(postId) >> Optional.ofNullable(post)
        cachedReadCountProvider.size == 0
    }
}

