package base.domain.member.repository;

import static base.domain.member.entity.QMember.member;

import base.domain.member.entity.Member;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {

  private final JPAQueryFactory jpaQueryFactory;

  @Override
  public Optional<Member> findByMemberEmailUsingQuerydsl(final String memberEmail) {
    return Optional.of(
        jpaQueryFactory.selectFrom(member)
            .where(member.memberEmail.eq(memberEmail))
            .fetchFirst()
    );
  }
}
