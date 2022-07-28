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
    UserClassRepository userClassRepository;

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

        UserClass userClass = userClassRepository.findByStudentId(user.getUserId()).get();

        Conference conference = conferenceRepositorySupport.findByClassesClassIdActive(userClass.getUserClassId()).get();


        testRecord.setUser(user);
        testRecord.setTest(test);

        testRecord.setDate(conference.getDate());
        System.out.println("test======================="+testRecord.getDate());
        return testRecordRepository.save(testRecord);
    }

    @Override
    public List<TestRecord> getAllTestRecordByUserId(Long userId) {

        List<TestRecord> testRecords = testRecordRepository.findByUserUserId(userId).get();

        return testRecords;
    }
}
