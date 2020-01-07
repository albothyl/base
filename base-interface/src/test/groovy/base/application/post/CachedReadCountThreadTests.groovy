package base.application.post

import base.domain.post.PostRepository
import base.domain.post.cache.CachedPostReadCount
import base.domain.post.entity.Post
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import spock.lang.Specification

import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executor
import java.util.concurrent.Executors

@ExtendWith(MockitoExtension.class)
class CachedReadCountThreadTests extends Specification {

    private PostRepository postRepository = Stub(PostRepository)
    private CachedPostReadCountProvider cachedReadCountProvider = new CachedPostReadCountProvider(postRepository)


    def "thread safe test"() {
        given:
        def threadCount = 30
        def loop = 1000
        def postId = 1
        CountDownLatch latch = new CountDownLatch(loop)
        Executor executor = Executors.newFixedThreadPool(threadCount)
        Post post = Post.builder()
                .postId(postId)
                .build()

        when:
        postRepository.findById(postId) >> Optional.ofNullable(post)
        and:
        (1..loop).each {
            executor.execute({
                Optional<CachedPostReadCount> cachedReadCount = cachedReadCountProvider.get(postId)
                cachedReadCount.get().increaseCount()
                latch.countDown()
            } as Runnable)
        }
        latch.await()

        then:
        long getCount = cachedReadCountProvider.get(postId).get().getCount()
        and:
        getCount == loop
    }
}
