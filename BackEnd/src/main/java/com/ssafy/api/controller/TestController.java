package com.ssafy.api.controller;

import com.google.gson.Gson;
import com.ssafy.api.request.*;
import com.ssafy.api.service.FilesService;
import com.ssafy.api.service.TestService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Files;
import com.ssafy.db.entity.Test;
import com.ssafy.db.repository.ClassesRepository;
import com.ssafy.db.repository.TestRepository;
import io.swagger.annotations.*;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

@Api(value = "시험 API", tags = {"Tests"})
@RestController
@RequestMapping("/api/v1/tests")
public class TestController {

    @Autowired
    TestService testService;

    @Autowired
    ClassesRepository classesRepository;

    @Autowired
    TestRepository testRepository;

    @Autowired
    FilesService filesService;


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
            @RequestBody @ApiParam(value="시험 정보", required = true) TestRegisterPostReq testRegisterinfo) {
        //임의로 리턴된 Classes 인스턴스.


        Test test = testService.createTest(testRegisterinfo);



        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
    }

//    @PostMapping("/upload")
//    @ApiOperation(value = "시험지 업로드", notes = "<strong>시험지 업로드한다.</strong>")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "성공"),
//            @ApiResponse(code = 500, message = "서버 오류")
//    })
//    public ResponseEntity<? extends BaseResponseBody> uploadFile(
//            @RequestPart MultipartFile files) throws IOException {
//        //임의로 리턴된 Classes 인스턴스.
//
//        Files file = new Files();
//
//        String sourceFileName = files.getOriginalFilename();
//
//        String sourceFileNameExtension = FilenameUtils.getExtension(sourceFileName).toLowerCase();
//
//        FilenameUtils.removeExtension(sourceFileName);
//
//        File destinationFile;
//        String destinationFileName;
//        String fileUrl="C:\\Program Files (x86)\\saffy\\common-pjt-back\\S07P12A304\\BackEnd\\src\\main\\resources\\dist\\tests";
//
//        do{
//            destinationFileName =RandomStringUtils.randomAlphanumeric(32)+"."+sourceFileNameExtension;
//            destinationFile=new File(files+destinationFileName);
//        }while(destinationFile.exists());
//
//        destinationFile.getParentFile().mkdir();
//        files.transferTo(destinationFile);
//
//        file.setFilename(destinationFileName);
//        file.setFileOriName(sourceFileName);
//        file.setFileUrl(fileUrl);
//
//        filesService.save(file);
//
//
//
//        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
//    }


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

    @GetMapping("/idcheck/{testName}")
    @ApiOperation(value = "시험 명 중복 체크", notes = "시험 생성 시 시험 명 중복 체크 검사")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<? extends BaseResponseBody> testNameCheck(@PathVariable("testName") String testName) {

        boolean temp = testService.checkTestName(testName);
        System.out.println("Test ========="+temp);
        if (temp == true) {
            return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
        } else {
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "fail"));
        }
    }
}