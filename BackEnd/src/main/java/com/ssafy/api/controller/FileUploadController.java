package com.ssafy.api.controller;

import com.ssafy.api.service.FilesService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Files;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;


@Api(value = "시험지 API", tags = {"Files"})
@RestController
@RequestMapping("/api/v1/files")
public class FileUploadController {

    @Autowired
    FilesService filesService;

    @PostMapping("/upload")
    @ApiOperation(value = "시험지 업로드", notes = "<strong>시험지 업로드한다.</strong>")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<? extends BaseResponseBody> uploadFile(
            @RequestPart MultipartFile files) throws IOException {
        //임의로 리턴된 Classes 인스턴스.

        Files file = new Files();

        String sourceFileName = files.getOriginalFilename();
        System.out.println("test============="+sourceFileName);
        String sourceFileNameExtension = FilenameUtils.getExtension(sourceFileName).toLowerCase();

        FilenameUtils.removeExtension(sourceFileName);

        File destinationFile;
        String destinationFileName;
        String fileUrl="C:\\Program Files (x86)\\saffy\\common-pjt-back\\S07P12A304\\BackEnd\\src\\main\\resources\\dist\\tests\\";

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
