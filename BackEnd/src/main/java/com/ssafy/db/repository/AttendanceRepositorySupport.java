package com.ssafy.db.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.db.entity.AttendanceRecord;
import com.ssafy.db.entity.Conference;
import com.ssafy.db.entity.QAttendanceRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class AttendanceRepositorySupport {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    QAttendanceRecord qAttendanceRecord = QAttendanceRecord.attendanceRecord;

    public Optional<AttendanceRecord> findByUserIdAndConferenceIdLast(Long userId,Long conferenceId){
        AttendanceRecord attendanceRecord = jpaQueryFactory.select(qAttendanceRecord).from(qAttendanceRecord)
                .where(qAttendanceRecord.attEndTime.isNull())
                .where(qAttendanceRecord.conference.conferenceId.eq(conferenceId)).fetchOne();
        if(attendanceRecord == null) return Optional.empty();
        return Optional.ofNullable(attendanceRecord);
    };
}
