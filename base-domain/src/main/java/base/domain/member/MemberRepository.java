package base.domain.member;

import base.domain.member.entity.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom
	, JpaSpecificationExecutor<Member> {
	Optional<Member> findMemberByMemberEmail(String memberEmail);
}
