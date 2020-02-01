package base.domain.support.entity;

import java.time.LocalDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CreatedAndModifiedEntity.class)
public abstract class CreatedAndModifiedEntity_ {

	public static volatile SingularAttribute<CreatedAndModifiedEntity, LocalDateTime> createdAt;
	public static volatile SingularAttribute<CreatedAndModifiedEntity, LocalDateTime> modifiedAt;

	public static final String CREATED_AT = "createdAt";
	public static final String MODIFIED_AT = "modifiedAt";

}

