package com.ssafy.api.controller;

import com.ssafy.api.service.FilesServiceImpl;
import com.ssafy.api.service.TestService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Files;
import com.ssafy.db.repository.FilesRepository;
import com.ssafy.db.repository.TestRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Api(value = "시험지 API", tags = {"Files"})
@RestController
@RequestMapping("/api/v1/files")
public class FileUploadController {

    @Autowired
    FilesServiceImpl filesService;

    @Autowired
    FilesRepository filesRepository;

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
                                                                 @RequestPart MultipartFile files
                                                                 ) throws IOException {

        filesService.create(testId,files);

        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
    }

    @GetMapping("/download/{testId}")
    @ApiOperation(value = "시험지 다운로드", notes = "<strong>시험지를 다운로드한다.</strong>")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    @ResponseBody
    public ResponseEntity<byte[]> downloadFile(@PathVariable Long testId) throws IOException {

        Files file = filesRepository.findByTestTestId(testId).get();
        String filePath = file.getFileUrl() + file.getFilename();

        FileSystemResource imgFile = new FileSystemResource(filePath);

        return ResponseEntity.status(200).body(IOUtils.toByteArray(imgFile.getInputStream()));
    }
}