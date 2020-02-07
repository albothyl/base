package base.domain.post.entity;

import base.domain.post.constants.BoardType;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Post.class)
public abstract class Post_ extends base.domain.support.entity.CreatedAndModifiedEntity_ {

	public static volatile SingularAttribute<Post, BoardType> boardType;
	public static volatile ListAttribute<Post, Comment> comments;
	public static volatile SingularAttribute<Post, String> contents;
	public static volatile SingularAttribute<Post, Long> postId;
	public static volatile SingularAttribute<Post, String> title;
	public static volatile SingularAttribute<Post, Long> readCount;
	public static volatile SingularAttribute<Post, Long> memberId;

	public static final String BOARD_TYPE = "boardType";
	public static final String COMMENTS = "comments";
	public static final String CONTENTS = "contents";
	public static final String POST_ID = "postId";
	public static final String TITLE = "title";
	public static final String READ_COUNT = "readCount";
	public static final String MEMBER_ID = "memberId";

}

