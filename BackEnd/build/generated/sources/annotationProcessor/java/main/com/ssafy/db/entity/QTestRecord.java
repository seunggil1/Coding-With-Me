package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QTestRecord is a Querydsl query type for TestRecord
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTestRecord extends EntityPathBase<TestRecord> {

    private static final long serialVersionUID = 331618501L;

    public static final QTestRecord testRecord = new QTestRecord("testRecord");

    public final NumberPath<Integer> correctCount = createNumber("correctCount", Integer.class);

    public final StringPath sourceCode = createString("sourceCode");

    public final NumberPath<Long> testId = createNumber("testId", Long.class);

    public final NumberPath<Long> testRecordId = createNumber("testRecordId", Long.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QTestRecord(String variable) {
        super(TestRecord.class, forVariable(variable));
    }

    public QTestRecord(Path<? extends TestRecord> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTestRecord(PathMetadata metadata) {
        super(TestRecord.class, metadata);
    }

}

