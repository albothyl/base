package base.domain.support.entity;

import com.google.common.base.Preconditions;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.metamodel.SingularAttribute;

@UtilityClass
public class CommonSpecificationUtils {

  public static <T, R> Specification<T> equal(SingularAttribute<T, R> attribute, R value) {
    Preconditions.checkNotNull(value);

    return (root, query, builder) -> builder.equal(root.get(attribute), value);
  }

  public static <T, R extends Number> Specification<T> gt(SingularAttribute<T, R> attribute, R value) {
    Preconditions.checkNotNull(value);

    return (root, query, builder) -> builder.gt(root.get(attribute), value);
  }
}
