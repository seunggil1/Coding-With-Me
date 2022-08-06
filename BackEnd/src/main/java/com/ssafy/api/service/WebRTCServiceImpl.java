package com.ssafy.api.service;

import com.ssafy.api.request.WebTokenPostReq;
import com.ssafy.db.entity.Conference;
import com.ssafy.db.repository.ConferenceRepository;
import com.ssafy.db.repository.ConferenceRepositorySupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("WebRTCService")
@Transactional(readOnly = true)
public class WebRTCServiceImpl implements WebRTCService {

    @Autowired
    OpenViduService openViduService;

    @Autowired
    ConferenceRepositorySupport conferenceRepositorySupport;

    @Autowired
    ConferenceRepository conferenceRepository;

    @Override
    @Transactional
    public String joinUser(WebTokenPostReq webTokenPostReq) {
        Long userId = webTokenPostReq.getId();
        Long classId = webTokenPostReq.getClassName();
        String displayName = webTokenPostReq.getDisplayName();
        try {
            // TODO: 2022-08-04 : 주의 사항. classID != ConferenceID classID는 바뀌지 않지만 ConferenceID 수업이 열릴때마다 새롭게 생성된다.
            // TODO: 2022-08-05 : userId은 겹치면 안되지만 displayName은 동일해도 상관x, displayName = 화면에 표시이는 이름


            // TODO: 2022-08-04 : DB에서 해당 반의 활성화 된 강의가 있는지 찾기. 있으면 ConferenceID를 반환한다.
            Conference conference = conferenceRepositorySupport.findByClassesClassIdActive(classId).get();
            String WebRTCToken = "";
            if(conference != null){
                WebRTCToken = conference.getRtc_token();
            }
            // openvidu 서버에 해당 수업이 존재하지 않을 경우
            if (!openViduService.sessionExist(classId)) {
                // TODO: 2022-08-04 : DB에 is_active = false 처리.
                conference.setActive(false);
                conferenceRepository.save(conference);
            }

            // is_active = true인 Conference가 없을경우
            if (conference == null) {
                // TODO: 2022-08-04 : userID를 통해 선생인지 확인한다. 선생일 경우 진행하고, 학생일 경우 토큰 발급 없이 종료한다.
                // TODO: 2022-08-04 : ConferenceID를 랜덤하게 새롭게 만들어낸다.
                WebRTCToken = "fsefs";
                String token = openViduService.getToken(WebRTCToken, userId, displayName);
                // TODO: 2022-08-05 DB에 새로운 conference 생성
                
                return token;
            } else {
                // 현재 conference가 열려있을 경우.

                // TODO: 2022-08-05 : 같은 강의 식별자, 사용자 식별자를 가진 출입 기록을 가져온다.
                // TODO: 2022-08-05 : 만약 퇴장시간이 기록되지 않은 경우, 퇴장시간을 기록한다.
                // (웹에서 강제 종료했을경우 DB에 반영되지 않았을 수도 있음.)

                String token = openViduService.getToken(WebRTCToken, userId, displayName);

                return token;
            }

        } catch (Exception e) {
            System.out.println("joinUser error : " + e.getMessage());
            e.printStackTrace();
            return "";
        }
    }
}
