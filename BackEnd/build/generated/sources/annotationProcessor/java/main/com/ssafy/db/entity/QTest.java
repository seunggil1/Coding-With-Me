package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTest is a Querydsl query type for Test
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTest extends EntityPathBase<Test> {

    private static final long serialVersionUID = 846499668L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTest test = new QTest("test");

    public final QClasses classes;

    public final QFiles files;

    public final SimplePath<com.ssafy.api.request.TestCase> testcase = createSimple("testcase", com.ssafy.api.request.TestCase.class);

    public final NumberPath<Long> testId = createNumber("testId", Long.class);

    public final StringPath testName = createString("testName");

    public final NumberPath<Integer> testQno = createNumber("testQno", Integer.class);

    public final ListPath<TestRecord, QTestRecord> testRecords = this.<TestRecord, QTestRecord>createList("testRecords", TestRecord.class, QTestRecord.class, PathInits.DIRECT2);

    public QTest(String variable) {
        this(Test.class, forVariable(variable), INITS);
    }

    public QTest(Path<? extends Test> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTest(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTest(PathMetadata metadata, PathInits inits) {
        this(Test.class, metadata, inits);
    }

    public QTest(Class<? extends Test> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.classes = inits.isInitialized("classes") ? new QClasses(forProperty("classes"), inits.get("classes")) : null;
        this.files = inits.isInitialized("files") ? new QFiles(forProperty("files"), inits.get("files")) : null;
    }

}

