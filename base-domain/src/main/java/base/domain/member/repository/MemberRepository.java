package base.domain.member.repository;

import base.domain.member.entity.Member;
import base.domain.support.repository.JpaAndSpecificationRepository;

import java.util.Optional;

public interface MemberRepository extends JpaAndSpecificationRepository<Member, Long>, MemberRepositoryCustom {
	Optional<Member> findMemberByMemberEmail(String memberEmail);
}
