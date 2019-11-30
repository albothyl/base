package base.domain.post;

import base.domain.post.entity.Post;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import java.util.List;

import static base.domain.post.entity.QPost.*;
import static base.domain.member.entity.QMember.*;

@RequiredArgsConstructor
public class PostRepositoryCustomImpl implements PostRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    //distinct 제거
    //기술적인 메소드 네임 제거
    @Override
    public List<Post> findAllLeftJoinWithMember() {
        return queryFactory.selectFrom(post)
                           .leftJoin(post.member, member)
                           .distinct()
                           .fetch();
    }
}