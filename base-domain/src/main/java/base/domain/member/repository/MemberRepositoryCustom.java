package base.domain.member.repository;

import base.domain.member.entity.Member;

import java.util.List;

public interface MemberRepositoryCustom {

  List<Member> findByDormantMember();

}
