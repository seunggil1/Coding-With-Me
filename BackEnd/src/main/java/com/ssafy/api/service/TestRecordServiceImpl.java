package com.ssafy.api.service;

import com.ssafy.api.request.TestRecordRegisterPostReq;
import com.ssafy.db.entity.Test;
import com.ssafy.db.entity.TestRecord;
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.TestRecordRepository;
import com.ssafy.db.repository.TestRepository;
import com.ssafy.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service("TestRecordService")
@Transactional(readOnly = true)
public class TestRecordServiceImpl implements TestRecordService {

    @Autowired
    TestRecordRepository testRecordRepository;

    @Autowired
    TestRepository testRepository;

    @Autowired
    UserRepository userRepository;


    @Override
    @Transactional
    public TestRecord createTestRecord(TestRecordRegisterPostReq testRecordRegisterPostReq) {
        TestRecord testrecord = new TestRecord();

        Test test = testRepository.findByClassesClassIdAndTestName(testRecordRegisterPostReq.getClassId(),testRecordRegisterPostReq.getTestName()).get();

        testrecord.setTest(test);
        testrecord.setSourceCode(testRecordRegisterPostReq.getSourceCode());


        User user = userRepository.findById(testRecordRegisterPostReq.getUserId()).get();

        testrecord.setUser(user);
        Date date =new Date();
        testrecord.setDate(date);

        return testRecordRepository.save(testrecord);

    }
}
