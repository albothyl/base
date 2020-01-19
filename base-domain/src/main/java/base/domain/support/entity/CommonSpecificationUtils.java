package base.domain.support.entity;

import javax.persistence.metamodel.SingularAttribute;
import org.springframework.data.jpa.domain.Specification;

public class CommonSpecificationUtils {

  public static <T, R> Specification<T> equal(SingularAttribute<T, R> attribute, R value) {
    assert value != null;

    return (root, query, builder) -> builder.equal(root.get(attribute), value);
  }

  public static <T, R extends Number> Specification<T> gt(SingularAttribute<T, R> attribute, R value) {
    assert value != null;

    return (root, query, builder) -> builder.gt(root.get(attribute), value);
  }
}
