package com.ssafy.api.controller;

import com.ssafy.api.request.TestRecordRegisterPostReq;
import com.ssafy.api.request.TestRegisterPostReq;
import com.ssafy.api.service.TestRecordService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Test;
import com.ssafy.db.entity.TestRecord;
import io.swagger.annotations.*;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "시험 이력 API", tags = {"Records"})
@RestController
@RequestMapping("/api/v1/records")


public class TestRecordController {
    @Autowired
    TestRecordService testRecordService;

    @PostMapping("/test")
    @ApiOperation(value = "시험 이력 생성", notes = "<strong>사용자 식별자와 시험 이름을 입력받아</strong> 시험이력을 만든다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<? extends BaseResponseBody> register(
            @RequestBody @ApiParam(value="시험 이력 정보", required = true) TestRecordRegisterPostReq testRecordRegisterInfo) {

        //임의로 리턴된 Classes 인스턴스.
        TestRecord testRecord = testRecordService.createTestRecord(testRecordRegisterInfo);

        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
    }
}
