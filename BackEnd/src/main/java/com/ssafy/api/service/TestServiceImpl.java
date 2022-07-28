package com.ssafy.api.service;


import com.ssafy.api.request.TestCase;
import com.ssafy.api.request.TestModifyPostReq;
import com.ssafy.api.request.TestRegisterPostReq;
import com.ssafy.db.entity.Classes;
import com.ssafy.db.entity.Test;
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.ClassesRepository;
import com.ssafy.db.repository.TestRepository;
import com.ssafy.db.repository.TestRepositorySupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("testService")
@Transactional(readOnly = true)
public class TestServiceImpl implements TestService {

    @Autowired
    TestRepository testRepository;

    @Autowired
    TestRepositorySupport testRepositorySupport;

    @Autowired
    ClassesRepository classesRepository;

    @Override
    @Transactional
    public Test createTest(TestRegisterPostReq testRegisterInfo) {
        Test test = new Test();
        test.setTestName(testRegisterInfo.getTestName());
        test.setTestQno(testRegisterInfo.getTestQno());
        test.setTestPath(testRegisterInfo.getTestPath());
        TestCase tc = new TestCase();
        tc.setTestcaseList(testRegisterInfo.getTestcaseList());
        test.setTestcase(tc);

        Classes classes = classesRepository.findByClassId(testRegisterInfo.getClassId()).get();
        test.setClasses(classes);


        return testRepository.save(test);
    }

    @Override
    @Transactional
    public Test modifyTest(TestModifyPostReq testModifyPostReq) {
        Test test = testRepository.findByClassesClassIdAndTestName(testModifyPostReq.getClassId(), testModifyPostReq.getTestName()).get();
        test.setTestName(testModifyPostReq.getNewtestName());
        test.setTestQno(testModifyPostReq.getTestQno());
        test.setTestPath(testModifyPostReq.getTestPath());
//        test.setTestcase(testModifyPostReq.getTestcase());

        return testRepository.save(test);
    }

    @Override
    @Transactional
    public boolean deleteTest(TestRegisterPostReq testRegisterPostReq) {
        boolean success = false;
        try {
            Test test = testRepository.findByClassesClassIdAndTestName(testRegisterPostReq.getClassId(), testRegisterPostReq.getTestName()).get();

            testRepository.delete(test);

            success = true;
        } catch (Exception e) {
            success = false;
        }
        return success;
    }

    @Override
    @Transactional
    public List<Test> getAllTestInfo(Long classId) {

        Classes classes = classesRepository.findByClassId(classId).get();

        List<Test> test = testRepository.findByClassesClassId(classes.getClassId()).get();
        return test;
    }

    @Override
    public boolean checkTestName(String testName) {
        boolean result = testRepositorySupport.findByTestNameEquals(testName);
        return result;
    }

}
