package base.domain.member.repository;

import base.domain.member.entity.Member;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static base.domain.member.entity.QMember.member;

@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {

  private final JPAQueryFactory jpaQueryFactory;

  @Override
  public List<Member> findByDormantMember() {
    return jpaQueryFactory.selectFrom(member)
            .where(member.account.accountNonLocked.eq(false))
            .fetch();
  }
}
