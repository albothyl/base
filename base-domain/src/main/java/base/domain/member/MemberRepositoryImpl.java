package base.domain.member;

import base.domain.member.entity.Member;
import static base.domain.member.entity.QMember.member;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {

  private final JPAQueryFactory jpaQueryFactory;

  @Override
  public Optional<Member> findMemberByMemberEmailUsingQuerydsl(String memberEmail) {
    return Optional.of(
        jpaQueryFactory.selectFrom(member)
            .where(member.memberEmail.eq(memberEmail))
            .fetchFirst()
    );
  }
}
