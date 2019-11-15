package com.study.base.domain.support.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QCreatedAndModifiedEntity is a Querydsl query type for CreatedAndModifiedEntity
 */
@Generated("com.querydsl.codegen.SupertypeSerializer")
public class QCreatedAndModifiedEntity extends EntityPathBase<CreatedAndModifiedEntity> {

    private static final long serialVersionUID = 1544206632L;

    public static final QCreatedAndModifiedEntity createdAndModifiedEntity = new QCreatedAndModifiedEntity("createdAndModifiedEntity");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> modifiedAt = createDateTime("modifiedAt", java.time.LocalDateTime.class);

    public QCreatedAndModifiedEntity(String variable) {
        super(CreatedAndModifiedEntity.class, forVariable(variable));
    }

    public QCreatedAndModifiedEntity(Path<? extends CreatedAndModifiedEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCreatedAndModifiedEntity(PathMetadata metadata) {
        super(CreatedAndModifiedEntity.class, metadata);
    }

}

