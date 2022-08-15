package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTestRecord is a Querydsl query type for TestRecord
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTestRecord extends EntityPathBase<TestRecord> {

    private static final long serialVersionUID = 331618501L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTestRecord testRecord = new QTestRecord("testRecord");

    public final NumberPath<Integer> correctCount = createNumber("correctCount", Integer.class);

    public final DatePath<java.util.Date> date = createDate("date", java.util.Date.class);

    public final StringPath lang = createString("lang");

    public final SimplePath<com.ssafy.api.request.SourceCode> sourceCode = createSimple("sourceCode", com.ssafy.api.request.SourceCode.class);

    public final QTest test;

    public final NumberPath<Long> testRecordId = createNumber("testRecordId", Long.class);

    public final QUser user;

    public QTestRecord(String variable) {
        this(TestRecord.class, forVariable(variable), INITS);
    }

    public QTestRecord(Path<? extends TestRecord> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTestRecord(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTestRecord(PathMetadata metadata, PathInits inits) {
        this(TestRecord.class, metadata, inits);
    }

    public QTestRecord(Class<? extends TestRecord> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.test = inits.isInitialized("test") ? new QTest(forProperty("test"), inits.get("test")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

