package com.ssafy.api.service;

import com.ssafy.api.request.TestRecordRegisterPostReq;
import com.ssafy.api.request.TestRegisterPostReq;
import com.ssafy.db.entity.*;
import com.ssafy.db.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service("testRecordService")
@Transactional(readOnly = true)
public class TestRecordServiceImpl implements TestRecordService {

    @Autowired
    TestRecordRepository testRecordRepository;

    @Autowired
    TestRepository testRepository;

    @Autowired
    ClassesRepository classesRepository;

    @Autowired
    ConferenceRepository conferenceRepository;

    @Autowired
    UserRepository userRepository;
    @Autowired
    ConferenceRepositorySupport conferenceRepositorySupport;

    @Override
    @Transactional
    public TestRecord createTestRecord(TestRecordRegisterPostReq testRecordRegisterInfo) {
        TestRecord testRecord = new TestRecord();

        testRecord.setSourceCode(testRecordRegisterInfo.getSourceCode());

        Test test=testRepository.findByClassesClassIdAndTestName(testRecordRegisterInfo.getClassId(),testRecordRegisterInfo.getTestName()).get();

        User user = userRepository.findByUserId(testRecordRegisterInfo.getUserId()).get();

        List<Classes> classes = classesRepository.findByUserUserId(user.getUserId()).get();

        List<Conference> conferences = conferenceRepository.findByClassesClassId(classes.get(0).getClassId()).get();


        testRecord.setUser(user);
        testRecord.setTest(test);

        testRecord.setDate(conferences.get(0).getDate());
        System.out.println("test======================="+testRecord.getDate());
        return testRecordRepository.save(testRecord);
    }
}
