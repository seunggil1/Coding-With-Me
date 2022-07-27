package com.ssafy.api.service;

import com.ssafy.api.request.TestModifyPostReq;
import com.ssafy.api.request.TestRegisterPostReq;
import com.ssafy.db.entity.Classes;
import com.ssafy.db.entity.Test;

import java.util.List;

public interface TestService {
    Test createTest(TestRegisterPostReq testRegisterPostReq);

    Test modifyTest(TestModifyPostReq testModifyPostReq);

    boolean deleteTest(TestRegisterPostReq testRegisterPostReq);

    List<Test> getAllTestInfo(Long classId);
}
