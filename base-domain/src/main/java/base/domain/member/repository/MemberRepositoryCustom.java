package base.domain.member.repository;

import base.domain.member.entity.Member;
import java.util.Optional;

public interface MemberRepositoryCustom {

  Optional<Member> findMemberByMemberEmailUsingQuerydsl(String memberEmail);
}
