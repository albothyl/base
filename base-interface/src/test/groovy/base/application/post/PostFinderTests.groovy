package base.application.post

import base.domain.post.PostRepository
import base.domain.post.entity.Post
import base.exception.ResourceNotFoundException
import io.github.benas.randombeans.EnhancedRandomBuilder
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import spock.lang.Specification

@ExtendWith(MockitoExtension.class)
class PostFinderTests extends Specification {

    def postRepository = Mock(PostRepository)
    def postFinder = new PostFinder(postRepository)


    def "postFinder 게시글 아이디 조회 테스트"(){
        given:
        def post = EnhancedRandomBuilder.aNewEnhancedRandomBuilder().build().nextObject(Post.class)

        when:
        def result = postFinder.findByPostId(post.getPostId())

        then:
        1 * postRepository.findById(post.getPostId()) >> Optional.ofNullable(post)

        and:
        result == post
    }

    def "postFinder 게시글 아이디 조회 ResourceNotFound 예외 테스트"(){
        given:
        def post = EnhancedRandomBuilder.aNewEnhancedRandomBuilder().build().nextObject(Post.class)

        when:
        postFinder.findByPostId(post.getPostId())

        then:
        1 * postRepository.findById(post.getPostId()) >> Optional.ofNullable(null)

        and:
        def e = thrown(ResourceNotFoundException.class)
        e.message == "PostId " + post.getPostId() + " not found"
    }
}
