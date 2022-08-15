package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QClasses is a Querydsl query type for Classes
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QClasses extends EntityPathBase<Classes> {

    private static final long serialVersionUID = 299818532L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QClasses classes = new QClasses("classes");

    public final StringPath classDescription = createString("classDescription");

    public final NumberPath<Long> classId = createNumber("classId", Long.class);

    public final StringPath className = createString("className");

    public final ListPath<Conference, QConference> conferences = this.<Conference, QConference>createList("conferences", Conference.class, QConference.class, PathInits.DIRECT2);

    public final ListPath<Test, QTest> testList = this.<Test, QTest>createList("testList", Test.class, QTest.class, PathInits.DIRECT2);

    public final QUser user;

    public final ListPath<UserClass, QUserClass> userClassList = this.<UserClass, QUserClass>createList("userClassList", UserClass.class, QUserClass.class, PathInits.DIRECT2);

    public QClasses(String variable) {
        this(Classes.class, forVariable(variable), INITS);
    }

    public QClasses(Path<? extends Classes> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QClasses(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QClasses(PathMetadata metadata, PathInits inits) {
        this(Classes.class, metadata, inits);
    }

    public QClasses(Class<? extends Classes> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

