package com.ssafy.api.controller;

import javax.servlet.http.HttpSession;

import com.ssafy.api.request.ForceClosePostReq;
import com.ssafy.api.request.LeaveSessionPostReq;
import com.ssafy.api.request.WebTokenPostReq;
import com.ssafy.api.service.OpenViduService;
import com.ssafy.api.service.WebRTCService;
import com.ssafy.common.model.response.BaseResponseBody;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/conference")
public class WebRTCController {

    // TODO: 2022-08-05 코딩전 확인해야될 용어
    // userId : 각 사용자 ID, DB에서 User.userId와 동일
    // classID : DB에서 classes.classId와 동일
    // ConferenceID : DB에서 Conference.conferenceId와 동일
    @Autowired
    OpenViduService openViduService;

    @Autowired
    WebRTCService webRTCService;

    @PostMapping("/getToken")
    @ApiOperation(value = "화상회의 토큰 발급", notes = "사용자의 정보를 통해 맞는 수업의 토큰을 발급한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<Map<String, Object>> joinUser(
            @RequestBody @ApiParam(value = "토큰 발급 정보", required = true) WebTokenPostReq webTokenPostReq
    ) {
        Map<String, Object> map =new HashMap<>();
        try {
           String token =  webRTCService.joinUser(webTokenPostReq);
           map.put("message", "Success");
           map.put("token", token);
            return ResponseEntity.status(200).body(map);
        } catch (Exception e) {
            map.put("message", e.getMessage());
            return ResponseEntity.status(404).body(map);
        }
    }

    @PostMapping("/leaveSession")
    @ApiOperation(value = "화상회의 종료", notes = "사용자가 수업에서 나갈때 호출하는 API. DB에 관련 내용을 업데이트 한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<Map<String, Object>> removeUser(
            @RequestBody @ApiParam(value = "화상 회의 종료 정보", required = true) LeaveSessionPostReq leaveSessionPostReq
    ) throws Exception {
        Map<String, Object> map =new HashMap<>();
        try {
            map.put("message", "Success");
            return ResponseEntity.status(200).body(map);
        }catch (Exception e){
            map.put("message", e.getMessage());
            return ResponseEntity.status(500).body(map);
        }

    }

    @PostMapping("/forceCloseSession")
    @ApiOperation(value = "화상회의 전체종료", notes = "해당 방을 전체 종료하고 모든 유저를 밖으로 내보내는 API.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<Map<String, Object>>  forceCloseSession(
            @RequestBody @ApiParam(value = "회의 전체 종료 정보", required = true)ForceClosePostReq forceClosePostReq
    ) throws Exception {

        Map<String, Object> map =new HashMap<>();
        try {
            map.put("message", "Success");
            return ResponseEntity.status(200).body(map);
        }catch (Exception e){
            map.put("message", e.getMessage());
            return ResponseEntity.status(500).body(map);
        }
    }

}