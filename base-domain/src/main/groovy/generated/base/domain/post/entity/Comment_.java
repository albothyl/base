package base.domain.post.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Comment.class)
public abstract class Comment_ extends base.domain.support.entity.CreatedAndModifiedEntity_ {

	public static volatile SingularAttribute<Comment, String> contents;
	public static volatile SingularAttribute<Comment, Long> commentId;
	public static volatile SingularAttribute<Comment, Long> postId;
	public static volatile SingularAttribute<Comment, Long> memberId;

	public static final String CONTENTS = "contents";
	public static final String COMMENT_ID = "commentId";
	public static final String POST_ID = "postId";
	public static final String MEMBER_ID = "memberId";

}

