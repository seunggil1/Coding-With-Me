package com.ssafy.api.controller;

import com.ssafy.api.request.ClassesModifyPostReq;
import com.ssafy.api.request.ClassesRegisterPostReq;
import com.ssafy.api.request.TestModifyPostReq;
import com.ssafy.api.request.TestRegisterPostReq;
import com.ssafy.api.service.TestService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Classes;
import com.ssafy.db.entity.Test;
import com.ssafy.db.repository.ClassesRepository;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "시험 API", tags = {"Tests"})
@RestController
@RequestMapping("/api/v1/tests")
public class TestController {

    @Autowired
    TestService testService;

    @Autowired
    ClassesRepository classesRepository;


    @GetMapping("/{classId}")
    @ApiOperation(value = "반시험 조회", notes = "해당 반의 시험정보를 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<? extends BaseResponseBody> getAllTestInfo(@PathVariable("classId") Long classId){

        List<Test> test = testService.getAllTestInfo(classId);

        return ResponseEntity.status(200).body(BaseResponseBody.of(200, String.valueOf(test)));
    }


    @PostMapping()
    @ApiOperation(value = "시험 생성", notes = "<strong>시험 정보를 입력하여</strong> 시험을 만든다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<? extends BaseResponseBody> register(
            @RequestBody @ApiParam(value="시험 정보", required = true)TestRegisterPostReq testRegisterInfo) {

        //임의로 리턴된 Classes 인스턴스.
        Test test = testService.createTest(testRegisterInfo);

        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
    }

    @PutMapping
    @ApiOperation(value = "시험 정보 수정", notes = "시험 정보를 수정한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<? extends BaseResponseBody> ModifyTestInfo(
            @RequestBody @ApiParam(value="수정 된 시험 정보", required = true) TestModifyPostReq testModifyPostReq) {

        Test test= testService.modifyTest(testModifyPostReq);

        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
    }

    @DeleteMapping
    @ApiOperation(value = "시험 삭제", notes = "시험을 삭제한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<? extends BaseResponseBody> deleteUser(
            @RequestBody @ApiParam(value="시험 정보", required = true) TestRegisterPostReq testRegisterInfo) {

        //boolean success = classesService.deleteClass(registerInfo);
        boolean success =testService.deleteTest(testRegisterInfo);

        if(success){
            return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
        }else{
            return ResponseEntity.status(404).body(BaseResponseBody.of(404, "Fail"));
        }

    }
}
