package base.domain.post;

import base.domain.member.entity.QMember;
import base.domain.post.entity.Comment;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

import static base.domain.post.entity.QComment.*;

@RequiredArgsConstructor
public class CommentRepositoryCustomImpl implements CommentRepositoryCustom {
    // queryDsl 사용법 좀더 찾아보기
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Comment> findByPostId(Long postId, Pageable pageable) {
        QueryResults results = queryFactory.from(comment)
                .leftJoin(comment.member, QMember.member)
                .where(comment.post.postId.eq(postId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        return new PageImpl<>(results.getResults(), pageable, results.getTotal());
    }
    //id -> commentId 변경
    @Override
    public Optional<Comment> findByCommentIdAndPostId(Long id, Long postId) {
        //selects 변경
        Comment selects = queryFactory.selectFrom(comment)
                .leftJoin(comment.member, QMember.member)
                .where(comment.commentId.eq(id))
                .where(comment.post.postId.eq(postId))
                .fetchOne();
        return Optional.ofNullable(selects);
    }
}
