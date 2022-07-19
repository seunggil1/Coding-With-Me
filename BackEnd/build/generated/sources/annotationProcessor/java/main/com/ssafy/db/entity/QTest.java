package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QTest is a Querydsl query type for Test
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTest extends EntityPathBase<Test> {

    private static final long serialVersionUID = 846499668L;

    public static final QTest test = new QTest("test");

    public final NumberPath<Long> classId = createNumber("classId", Long.class);

    public final StringPath testcase = createString("testcase");

    public final NumberPath<Long> testId = createNumber("testId", Long.class);

    public final StringPath testName = createString("testName");

    public final StringPath testPath = createString("testPath");

    public final NumberPath<Integer> testQno = createNumber("testQno", Integer.class);

    public QTest(String variable) {
        super(Test.class, forVariable(variable));
    }

    public QTest(Path<? extends Test> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTest(PathMetadata metadata) {
        super(Test.class, metadata);
    }

}

