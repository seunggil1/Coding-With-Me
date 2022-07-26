package com.ssafy.api.service;

import com.ssafy.api.request.TestModifyPostReq;
import com.ssafy.api.request.TestRegisterPostReq;
import com.ssafy.db.entity.Test;

public interface TestService {
    Test createTest(TestRegisterPostReq testRegisterPostReq);

    Test modifyTest(TestModifyPostReq testModifyPostReq);

    boolean deleteTest(TestRegisterPostReq testRegisterPostReq);
}
