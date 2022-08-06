package com.ssafy.api.controller;

import javax.servlet.http.HttpSession;

import com.ssafy.api.request.WebTokenPostReq;
import com.ssafy.api.service.OpenViduService;
import com.ssafy.common.model.response.BaseResponseBody;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    }

    @RequestMapping(value = "/leaveSession", method = RequestMethod.POST)
    @ApiOperation(value = "화상회의 종료", notes = "사용자가 수업에서 나갈때 호출하는 API. DB에 관련 내용을 업데이트 한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    // TODO: 2022-08-04 : Request 형식 정하기
    public String removeUser(
            @RequestParam(name = "conferenceID") String conferenceID,
            @RequestParam(name = "token") String token) throws Exception {

        // 해당 토큰이 누구의 토큰인지 찾는다.
        String user = openViduService.findUser(conferenceID, token);
        // TODO: 2022-08-05 : 출입기록에 퇴장시간 기록


        // 방에서 해당 유저를 제거한 뒤, 해당 방에 더 이상 아무도 남지 않았으면 true 반환
        boolean isEmpty = openViduService.removeUser(conferenceID, token);
        if (isEmpty) {
            // TODO: 2022-08-05 : DB에 해당 confernce is_active = false 처리.
        }

        return "success";
    }

    @RequestMapping(value = "/forceCloseSession", method = RequestMethod.POST)
    @ApiOperation(value = "화상회의 강제종료", notes = "해당 방을 강제로 종료하고 모든 유저를 밖으로 내보내는 API.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    // TODO: 2022-08-04 : Request 형식 정하기
    public String forceCloseSession(
            @RequestParam(name = "ConferenceID") String ConferenceID,
            @RequestParam(name = "token") String token) throws Exception {

        // TODO: 2022-08-05 : is_active = true인 해당 conference가 존재하는지 check
        // TODO: 2022-08-05 : 요청한 user가 해당 conference의 owner_id와 일치 하는지 check

        if (ConferenceID != null) {
            openViduService.closeSession(ConferenceID);
            // TODO: 2022-08-05 : DB에 해당 confernce is_active = false 처리.
            // TODO: 2022-08-05 : 출입 기록 받아와서 퇴장시간이 null인 데이터를 현재 시간으로 변경
        }

        return "success";
    }

    @ApiOperation(value = "해당 반의 conference status", notes = "요청한 반의 class Conference가 열려있는지 찾아서 반환한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    @GetMapping("/status/{classID}")
    // TODO: 2022-08-05 : Request 형식 정하기
    public String conferenceStatus(@PathVariable String classID) {

        // TODO: 2022-08-05 : 요청한 user가 해당 classID 소속인지 check

        // TODO: 2022-08-05 : is_active = true이고 classID가 일치하는 conference이 있는지 check. 없으면 Exist 반환

        // TODO: 2022-08-05 : conference가 존재하면 sessionID를 받아와서 openvidu에서 check
        String sessionId = "";
        if (openViduService.sessionExist(sessionId)) {
            return "Exist";
        } else {
            // TODO: 2022-08-05 : 해당 confernce is_active = false 처리.

            return "Not Exist";
        }
    }

}