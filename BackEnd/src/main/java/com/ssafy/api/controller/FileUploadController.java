package com.ssafy.api.controller;

import com.ssafy.api.request.FileRegisterPostReq;
import com.ssafy.api.request.TestRegisterPostReq;
import com.ssafy.api.service.FilesService;
import com.ssafy.api.service.TestService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Files;
import com.ssafy.db.entity.Test;
import com.ssafy.db.repository.TestRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;


@Api(value = "시험지 API", tags = {"Files"})
@RestController
@RequestMapping("/api/v1/files")
public class FileUploadController {

    @Autowired
    FilesService filesService;

    @Autowired
    TestService testService;

    @Autowired
    TestRepository testRepository;

    @PostMapping("/upload/{testId}")
    @ApiOperation(value = "시험지 업로드", notes = "<strong>시험지 업로드한다.</strong>")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<? extends BaseResponseBody> uploadFile(@PathVariable Long testId,
            @RequestPart MultipartFile files) throws IOException {
        //임의로 리턴된 Classes 인스턴스.

        Files file = new Files();

        Test test= testRepository.findByTestId(testId).get();
        file.setTest(test);
        String sourceFileName = files.getOriginalFilename();
        System.out.println("test============="+sourceFileName);
        String sourceFileNameExtension = FilenameUtils.getExtension(sourceFileName).toLowerCase();

        FilenameUtils.removeExtension(sourceFileName);

        File destinationFile;
        String destinationFileName;
        String fileUrl="/home/ubuntu/upload/";

        do{
            destinationFileName = RandomStringUtils.randomAlphanumeric(32)+"."+sourceFileNameExtension;
            destinationFile=new File(fileUrl+destinationFileName);
        }while(destinationFile.exists());

        System.out.println("test="+destinationFile.getParentFile());
        destinationFile.getParentFile().mkdirs();
        System.out.println("test="+destinationFile.getParentFile());

        files.transferTo(destinationFile);



        file.setFilename(destinationFileName);

        file.setFileOriName(sourceFileName);

        file.setFileUrl(fileUrl);

        filesService.save(file);



        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
    }
}
