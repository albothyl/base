package base.domain.post;

import base.domain.post.entity.Post;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;


import static base.domain.member.entity.QMember.*;
import static base.domain.post.entity.QComment.*;
import static base.domain.post.entity.QPost.*;

@RequiredArgsConstructor
public class PostRepositoryCustomImpl implements PostRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Post> findAllFetchJoin() {
        return queryFactory.selectFrom(post)
                           .leftJoin(post.comments, comment)
                           .fetchJoin()
                           .distinct()
                           .fetch();
    }
}