package base.domain.post;

import base.domain.post.entity.Post;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static base.domain.post.entity.QPost.post;
import static base.domain.post.entity.QComment.comment;

@RequiredArgsConstructor
public class PostRepositoryCustomImpl implements PostRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Post> findByIdWithComments(Long postId) {
        return queryFactory.selectFrom(post)
                .where(post.postId.eq(postId))
                .leftJoin(post.comments, comment)
                .fetchJoin()
                .distinct() //코멘트 테이블과 post 테이블 조인 시 중복이 발생하므로 distinct 설정
                .fetch();
    }
}