package com.ssafy.api.controller;

import com.ssafy.api.request.ConferenceModifyPostReq;
import com.ssafy.api.request.ConferenceRegisterPostReq;
import com.ssafy.api.service.ConferenceService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Conference;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "강의 API", tags = {"Conference"})
@RestController
@RequestMapping("/api/v1/conferences")
public class ConferenceController {
    @Autowired
    ConferenceService conferenceService;

    @PostMapping
    @ApiOperation(value = "[강사]강의실 개설", notes = "<strong>강사가 </strong> 강의실을 개설한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<? extends BaseResponseBody> registerTutor(
            @RequestBody @ApiParam(value="반 정보", required = true) ConferenceRegisterPostReq registerInfo) {

        //임의로 리턴된 Conference 인스턴스.
        Conference conference = conferenceService.createConference(registerInfo);

        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
    }

    @PostMapping
    @ApiOperation(value = "강의 정보 수정", notes = "강의에 대한 정보를 수정한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<? extends BaseResponseBody> modifyConferenceInfo(
            @RequestBody @ApiParam(value="강의실 정보", required = true) ConferenceModifyPostReq modifyInfo) {

        //임의로 리턴된 Conference 인스턴스.
        Conference conference = conferenceService.modifyConference(modifyInfo);

        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
    }
    @PostMapping("/end")
    @ApiOperation(value = "강의 정보 수정", notes = "강의가 종료되고 정보를 수정한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<? extends BaseResponseBody> finishConferenceInfo(
            @RequestBody @ApiParam(value="강의실 정보", required = true) ConferenceModifyPostReq modifyInfo) {

        //임의로 리턴된 Conference 인스턴스.
        Conference conference = conferenceService.finishConference(modifyInfo);

        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
    }
}
