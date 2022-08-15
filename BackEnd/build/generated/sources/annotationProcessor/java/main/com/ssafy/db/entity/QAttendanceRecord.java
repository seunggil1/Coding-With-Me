package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAttendanceRecord is a Querydsl query type for AttendanceRecord
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAttendanceRecord extends EntityPathBase<AttendanceRecord> {

    private static final long serialVersionUID = 1606934620L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAttendanceRecord attendanceRecord = new QAttendanceRecord("attendanceRecord");

    public final NumberPath<Long> attendanceId = createNumber("attendanceId", Long.class);

    public final TimePath<java.util.Date> attEndTime = createTime("attEndTime", java.util.Date.class);

    public final TimePath<java.util.Date> attStartTime = createTime("attStartTime", java.util.Date.class);

    public final QConference conference;

    public final DatePath<java.util.Date> date = createDate("date", java.util.Date.class);

    public final QUser user;

    public QAttendanceRecord(String variable) {
        this(AttendanceRecord.class, forVariable(variable), INITS);
    }

    public QAttendanceRecord(Path<? extends AttendanceRecord> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAttendanceRecord(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAttendanceRecord(PathMetadata metadata, PathInits inits) {
        this(AttendanceRecord.class, metadata, inits);
    }

    public QAttendanceRecord(Class<? extends AttendanceRecord> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.conference = inits.isInitialized("conference") ? new QConference(forProperty("conference"), inits.get("conference")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

