package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 846542477L;

    public static final QUser user = new QUser("user");

    public final ListPath<AttendanceRecord, QAttendanceRecord> attendanceRecords = this.<AttendanceRecord, QAttendanceRecord>createList("attendanceRecords", AttendanceRecord.class, QAttendanceRecord.class, PathInits.DIRECT2);

    public final DatePath<java.util.Date> birthDt = createDate("birthDt", java.util.Date.class);

    public final ListPath<Classes, QClasses> classlist = this.<Classes, QClasses>createList("classlist", Classes.class, QClasses.class, PathInits.DIRECT2);

    public final StringPath email = createString("email");

    public final StringPath id = createString("id");

    public final StringPath name = createString("name");

    public final StringPath nickname = createString("nickname");

    public final StringPath password = createString("password");

    public final StringPath phone = createString("phone");

    public final StringPath profilePath = createString("profilePath");

    public final StringPath role = createString("role");

    public final ListPath<TestRecord, QTestRecord> testRecords = this.<TestRecord, QTestRecord>createList("testRecords", TestRecord.class, QTestRecord.class, PathInits.DIRECT2);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

