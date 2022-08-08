package com.ssafy.api.service;

import com.ssafy.api.request.ForceClosePostReq;
import com.ssafy.api.request.LeaveSessionPostReq;
import com.ssafy.api.request.WebTokenPostReq;
import com.ssafy.db.entity.AttendanceRecord;
import com.ssafy.db.entity.Conference;
import com.ssafy.db.repository.AttendanceRepository;
import com.ssafy.db.repository.AttendanceRepositorySupport;
import com.ssafy.db.repository.ConferenceRepository;
import com.ssafy.db.repository.ConferenceRepositorySupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service("WebRTCService")
@Transactional(readOnly = true)
public class WebRTCServiceImpl implements WebRTCService {

    @Autowired
    OpenViduService openViduService;

    @Autowired
    ConferenceRepositorySupport conferenceRepositorySupport;

    @Autowired
    ConferenceRepository conferenceRepository;


    @Autowired
    AttendanceRepositorySupport attendanceRepositorySupport;

    @Autowired
    AttendanceRepository attendanceRepository;
    @Override
    @Transactional
    public String joinUser(WebTokenPostReq webTokenPostReq) throws Exception {
        Long userId = webTokenPostReq.getId();
        Long classId = webTokenPostReq.getClassId();
        String displayName = webTokenPostReq.getDisplayName();

        // TODO: 2022-08-04 : 주의 사항. classID != ConferenceID classID는 바뀌지 않지만 ConferenceID 수업이 열릴때마다 새롭게 생성된다.
        // TODO: 2022-08-05 : userId은 겹치면 안되지만 displayName은 동일해도 상관x, displayName = 화면에 표시이는 이름


        // TODO: 2022-08-04 : DB에서 해당 반의 활성화 된 강의가 있는지 찾기. 있으면 ConferenceID를 반환한다.
        Optional<Conference> Oconference = conferenceRepositorySupport.findByClassesClassIdActive(classId);
        Long conferenceId = -1L;

        if (Oconference.isPresent()) {
            Conference conference = Oconference.get();
            conferenceId = conference.getConferenceId();

            // openvidu 서버에 해당 수업이 존재하지 않을 경우
            if (!openViduService.sessionExist(conferenceId)) {
                // TODO: 2022-08-04 : DB에 is_active = false 처리.
                conference.setActive(false);
                conferenceRepository.save(conference);
                throw new Exception("방이 없서용");
            }
        }

        // 원래 방이 살아있었는데 튕겨서 퇴장 기록을 못 찍었어. 여기서 찍어줘
        // TODO: 2022-08-05 : 같은 강의 식별자, 사용자 식별자를 가진 출입 기록을 가져온다.
        // TODO: 2022-08-05 : 만약 퇴장시간이 기록되지 않은 경우, 퇴장시간을 기록한다.
        // (웹에서 강제 종료했을경우 DB에 반영되지 않았을 수도 있음.)
        //출입기록 해당 강의 식별자 돌려서 다 endtime 입력 해줘.

        Optional<AttendanceRecord> OattendanceRecord = attendanceRepositorySupport.findByUserIdAndConferenceIdLast(userId, conferenceId);

        if(OattendanceRecord.isPresent()){
            Date date = new Date();
            AttendanceRecord attendanceRecord=OattendanceRecord.get();
            attendanceRecord.setAttEndTime(date);
            attendanceRepository.save(attendanceRecord);
        }

        String token = openViduService.getToken(conferenceId, userId, displayName);

        return token;


    }

    @Override
    @Transactional
    public void removeUserSession(LeaveSessionPostReq leaveSessionPostReq) throws Exception{
        Long conferenceID = leaveSessionPostReq.getConferenceId();
        String token = leaveSessionPostReq.getToken();
       // 방에서 해당 유저를 제거한 뒤, 해당 방에 더 이상 아무도 남지 않았으면 true 반환
        boolean isEmpty = openViduService.removeUser(conferenceID, token);
        if (isEmpty) {
            Conference conference = conferenceRepository.findByConferenceId(conferenceID).get();
            conference.setActive(false);
            conferenceRepository.save(conference);
            // TODO: 2022-08-05 : DB에 해당 confernce is_active = false 처리.
        }
    }

    @Override
    public void forceCloseSession(ForceClosePostReq forceClosePostReq) throws Exception {

        Long classId = forceClosePostReq.getClassId();
        String ownerId = forceClosePostReq.getUserId();
        // TODO: 2022-08-05 : is_active = true인 해당 conference가 존재하는지 check
        // TODO: 2022-08-05 : 요청한 user가 해당 conference의 owner_id와 일치 하는지 check
        Conference conference = conferenceRepositorySupport.findByClassesClassIdActive(classId).get();

        if(conference.getOwnerId().equals(ownerId)){
            openViduService.closeSession(conference.getConferenceId());
            conference.setActive(false);
        }else{
            throw new Exception("잘못된 접근입니다.");
        }
    }
}
