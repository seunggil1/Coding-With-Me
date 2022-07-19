package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QAttendanceRecord is a Querydsl query type for AttendanceRecord
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAttendanceRecord extends EntityPathBase<AttendanceRecord> {

    private static final long serialVersionUID = 1606934620L;

    public static final QAttendanceRecord attendanceRecord = new QAttendanceRecord("attendanceRecord");

    public final NumberPath<Long> attendanceId = createNumber("attendanceId", Long.class);

    public final TimePath<java.util.Date> attEndTime = createTime("attEndTime", java.util.Date.class);

    public final TimePath<java.util.Date> attStartTime = createTime("attStartTime", java.util.Date.class);

    public final NumberPath<Long> conferenceId = createNumber("conferenceId", Long.class);

    public final DatePath<java.util.Date> date = createDate("date", java.util.Date.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QAttendanceRecord(String variable) {
        super(AttendanceRecord.class, forVariable(variable));
    }

    public QAttendanceRecord(Path<? extends AttendanceRecord> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAttendanceRecord(PathMetadata metadata) {
        super(AttendanceRecord.class, metadata);
    }

}

