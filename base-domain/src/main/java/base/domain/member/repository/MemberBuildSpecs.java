package base.domain.member.repository;

import base.domain.member.entity.Member;
import base.domain.member.entity.Member_;
import base.domain.support.entity.CommonSpecificationUtils;
import org.springframework.data.jpa.domain.Specification;

public class MemberBuildSpecs {

  public static Specification<Member> findMemberByMemberEmail(String memberEmail) {
    return Specification
            .where(CommonSpecificationUtils.equal(Member_.memberEmail, memberEmail));
  }
}
