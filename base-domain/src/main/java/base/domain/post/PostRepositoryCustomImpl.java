package base.domain.post;

import base.domain.post.entity.Post;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import static base.domain.post.entity.QComment.comment;
import static base.domain.post.entity.QPost.post;

@RequiredArgsConstructor
public class PostRepositoryCustomImpl implements PostRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Post findByIdWithComments(Long postId) {
        return queryFactory.selectFrom(post)
                .where(post.postId.eq(postId))
                .leftJoin(post.comments, comment)
                .fetchJoin()
                .fetchOne();
    }
}