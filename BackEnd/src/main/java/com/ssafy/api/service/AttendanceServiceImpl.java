package com.ssafy.api.service;

import com.ssafy.api.request.AttendanceRegisterPostReq;
import com.ssafy.db.entity.*;
import com.ssafy.db.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service("attendanceService")
@Transactional(readOnly = true)
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    AttendanceRepository attendanceRepository;

    @Autowired
    AttendanceRepositorySupport attendanceRepositorySupport;

    @Autowired
    ConferenceRepository conferenceRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public AttendanceRecord createAttendance(AttendanceRegisterPostReq attendanceRegisterPostReq) {
        AttendanceRecord attendance = new AttendanceRecord();

        Long userId = attendanceRegisterPostReq.getUserId();
        Long conferenceId = attendanceRegisterPostReq.getConferenceId();

        Conference conference = conferenceRepository.findByConferenceId(conferenceId).get();
        User user = userRepository.findByUserId(userId).get();
        Date date = new Date();


        attendance.setConference(conference); //현재 살아있는 강의를 가져와야 한다. 클래스 아이디가 필요
        attendance.setUser(user);
        attendance.setDate(date);
        attendance.setAttStartTime(date);

        return attendanceRepository.save(attendance);
    }

    @Override
    @Transactional
    public AttendanceRecord updateAttendance(AttendanceRegisterPostReq attendanceRegisterPostReq) {
        Long userId = attendanceRegisterPostReq.getUserId();
        Long conferenceId = attendanceRegisterPostReq.getConferenceId();
        AttendanceRecord attendanceRecord = attendanceRepositorySupport.findByUserIdAndConferenceIdLast(userId, conferenceId).get();
        Date date = new Date();
        attendanceRecord.setAttEndTime(date);

        return attendanceRepository.save(attendanceRecord);
    }
}
