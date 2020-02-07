package base.application.post

import base.domain.post.CommentRepository
import base.domain.post.entity.Comment
import base.exception.ResourceNotFoundException
import io.github.benas.randombeans.EnhancedRandomBuilder
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import spock.lang.Specification

@ExtendWith(MockitoExtension.class)
class CommentModifierTests extends Specification {

    def commentRepository = Mock(CommentRepository)
    def commentModifier = new CommentModifier(commentRepository)

    def "commentModifier 댓글 생성 테스트"() {
        given:
        def comment = EnhancedRandomBuilder.aNewEnhancedRandomBuilder().build().nextObject(Comment.class)

        when:
        def result = commentModifier.createComment(comment)

        then:
        1 * commentRepository.save(comment) >> comment

        and:
        result == comment
    }

    def "commentModifier 댓글 업데이트 테스트"() {
        given:
        def comment = EnhancedRandomBuilder.aNewEnhancedRandomBuilder().build().nextObject(Comment.class)
        def updateComment = "update comment"

        when:
        def result = commentModifier.updateComment(comment.getCommentId(), updateComment)

        then:
        1 * commentRepository.findById(comment.getCommentId()) >> Optional.ofNullable(comment)

        and:
        with(result) {
            contents == updateComment
        }
    }

    def "댓글 업데이트 ResourceNotFound 예외 테스트"() {
        given:
        def commentId = 1l
        def updateComment = "업데이트"

        when:
        commentModifier.updateComment(commentId, updateComment)

        then:
        1 * commentRepository.findById(commentId) >> Optional.ofNullable(null)

        and:
        thrown(ResourceNotFoundException.class)
    }

    def "comment 댓글 삭제 테스트"() {
        given:
        def comment = EnhancedRandomBuilder.aNewEnhancedRandomBuilder().build().nextObject(Comment.class)

        when:
        commentModifier.deleteComment(comment.getCommentId())

        then:
        1 * commentRepository.deleteById(comment.getCommentId())
    }
}
