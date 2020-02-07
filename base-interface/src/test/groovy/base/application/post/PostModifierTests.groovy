package base.application.post

import base.domain.post.PostRepository
import base.domain.post.entity.Post
import base.exception.ResourceNotFoundException
import io.github.benas.randombeans.EnhancedRandomBuilder
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import spock.lang.Specification

@ExtendWith(MockitoExtension.class)
class PostModifierTests extends Specification {

    def postRepository = Mock(PostRepository)
    def postModifier = new PostModifier(postRepository)


    def "post modifier 게시글 생성 테스트"() {
        given:
        def post = EnhancedRandomBuilder.aNewEnhancedRandomBuilder().build().nextObject(Post.class)

        when:
        def result = postModifier.createPost(post)

        then:
        1 * postRepository.save(post) >> post

        and:
        result == post
    }

    def "post modifier 게시글 업데이트 테스트"() {
        given:
        def post = EnhancedRandomBuilder.aNewEnhancedRandomBuilder().build().nextObject(Post.class)
        def updatePost = EnhancedRandomBuilder.aNewEnhancedRandomBuilder().build().nextObject(Post.class)

        when:
        def result = postModifier.updatePost(post.getPostId(), updatePost)

        then:
        postRepository.findById(post.getPostId()) >> Optional.ofNullable(post)

        and:
        with(result) {
            title == post.getTitle()
            contents == post.getContents()
            boardType == post.getBoardType()
        }
    }

    def "post modifier 게시글 업데이트 ResourceNotFound 예외 테스트"() {
        given:
        def post = EnhancedRandomBuilder.aNewEnhancedRandomBuilder().build().nextObject(Post.class)
        def updatePost = EnhancedRandomBuilder.aNewEnhancedRandomBuilder().build().nextObject(Post.class)

        when:
        postModifier.updatePost(post.getPostId(), updatePost)

        then:
        postRepository.findById(post.getPostId()) >> Optional.ofNullable(null)

        and:
        def e = thrown(ResourceNotFoundException.class)
        e.message == "PostId " + post.getPostId() + " not found"


    }

    def "post modifier 게시글 삭제 테스트"() {
        given:
        def post = EnhancedRandomBuilder.aNewEnhancedRandomBuilder().build().nextObject(Post.class)

        when:
        postModifier.deletePost(post.getPostId())

        then:
        1 * postRepository.deleteById(post.getPostId())
    }
}
