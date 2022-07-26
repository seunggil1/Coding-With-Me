package com.ssafy.api.service;

import com.ssafy.api.request.ClassesModifyPostReq;
import com.ssafy.api.request.ConferenceModifyPostReq;
import com.ssafy.api.request.ConferenceRegisterPostReq;
import com.ssafy.db.entity.Classes;
import com.ssafy.db.entity.Conference;
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.ClassesRepository;
import com.ssafy.db.repository.ConferenceRepository;
import com.ssafy.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
@Service("conferenceService")
@Transactional(readOnly = true)
public class ConferenceServiceImpl implements ConferenceService{

    @Autowired
    ConferenceRepository conferenceRepository;
    @Autowired
    ClassesRepository classesRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public Conference createConference(ConferenceRegisterPostReq conferenceRegisterPostReq) {
        Conference conference =new Conference();
        Classes classes = classesRepository.findByClassId(conferenceRegisterPostReq.getClassId()).get();
        conference.setClasses(classes);
        conference.setConferenceName(conferenceRegisterPostReq.getConferenceName());

        User user = userRepository.findById(conferenceRegisterPostReq.getOwnerId()).get();
        conference.setOwnerId(user.getId());

        Date date = new Date();
        conference.setDate(date);
        conference.setConfStartTime(date);
        conference.setActive(true);

//        conference.setRtc_token();
        return conferenceRepository.save(conference);
    }

    @Override
    @Transactional
    public Conference modifyConference(ConferenceModifyPostReq conferenceModifyPostReq) {
        Conference conference = conferenceRepository.findByClassesClassId(conferenceModifyPostReq.getClassId()).get();
        conference.setConferenceName(conferenceModifyPostReq.getConferenceName());
        User user = userRepository.findById(conferenceModifyPostReq.getOwnerId()).get();
        conference.setOwnerId(user.getId());
        return conferenceRepository.save(conference);
    }

    @Override
    @Transactional
    public Conference finishConference(ConferenceModifyPostReq conferenceModifyPostReq) {
        Conference conference = conferenceRepository.findByClassesClassId(conferenceModifyPostReq.getClassId()).get();
        conference.setActive(false);
        Date date = new Date();
        conference.setConfEndTime(date);
        return conferenceRepository.save(conference);
    }

}
