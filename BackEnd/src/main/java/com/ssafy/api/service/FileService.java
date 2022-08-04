package com.ssafy.api.service;

import com.ssafy.db.entity.Files;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {
    Files create(Long testId, MultipartFile multipartFile) throws IOException;
}
