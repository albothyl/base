package base.domain.board.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Board.class)
public abstract class Board_ {

	public static volatile SingularAttribute<Board, String> title;
	public static volatile SingularAttribute<Board, Long> seq;
	public static volatile SingularAttribute<Board, String> content;
	public static volatile SingularAttribute<Board, Date> createDate;

	public static final String TITLE = "title";
	public static final String SEQ = "seq";
	public static final String CONTENT = "content";
	public static final String CREATE_DATE = "createDate";

}

