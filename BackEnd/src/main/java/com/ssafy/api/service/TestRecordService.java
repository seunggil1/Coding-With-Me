package com.ssafy.api.service;

import com.ssafy.api.request.TestRecordRegisterPostReq;
import com.ssafy.db.entity.TestRecord;

public interface TestRecordService {
    TestRecord createTestRecord(TestRecordRegisterPostReq testRecordRegisterPostReq);


}
