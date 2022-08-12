package com.ssafy.api.service;

import com.ssafy.db.entity.Files;
import com.ssafy.db.entity.Test;
import com.ssafy.db.repository.FilesRepository;
import com.ssafy.db.repository.TestRepository;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
@Transactional(readOnly = true)
public class FilesServiceImpl implements FileService{

    @Autowired
    FilesRepository filesRepository;

    @Autowired
    TestRepository testRepository;

    @Override
    @Transactional
    public Files create(Long testId, MultipartFile multipartFile) throws IOException {
        Files file = new Files();

        Test test= testRepository.findByTestId(testId).get();

        String sourceFileName = multipartFile.getOriginalFilename();
        String sourceFileNameExtension = FilenameUtils.getExtension(sourceFileName).toLowerCase();
        FilenameUtils.removeExtension(sourceFileName);

        File destinationFile;
        String destinationFileName;
        String fileUrl="/home/ubuntu/upload/";
        //String fileUrl="C:\\Program Files (x86)\\saffy\\common-pjt-back\\S07P12A304\\BackEnd\\src\\main\\resources\\dist\\tests";

        do{
            destinationFileName = RandomStringUtils.randomAlphanumeric(32)+"."+sourceFileNameExtension;
            destinationFile=new File(fileUrl+destinationFileName);
        }while(destinationFile.exists());

        destinationFile.getParentFile().mkdirs();

        multipartFile.transferTo(destinationFile);


        file.setFilename(destinationFileName);
        file.setFileOriName(sourceFileName);
        file.setFileUrl(fileUrl);


        file.setTest(test);

        return filesRepository.save(file);
    }
}