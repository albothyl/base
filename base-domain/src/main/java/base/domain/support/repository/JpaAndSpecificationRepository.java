package base.domain.support.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @NoRepositoryBean은 애노테이션은
 *
 * 해당 레파지토리 빈이 Spring data 에 의해서 인스턴스화 될 필요가 없을 때 붙이는 애노테이션입니다.
 *
 * JpaAndSpecificationReposiotry의 경우 JpaRepository와 JpaSpecificationExecutor를 묶어서
 *
 * MemberRepository나 PostRepository에 상속해주는 중간다리 역할을 하기 때문에
 *
 * @NoRepositoryBean을 달아줘야 합니다.
 * @param <T>
 * @param <ID>
 */
@NoRepositoryBean
public interface JpaAndSpecificationRepository<T, ID> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {
}
