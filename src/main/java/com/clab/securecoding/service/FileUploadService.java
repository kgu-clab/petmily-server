package com.clab.securecoding.service;

import com.clab.securecoding.exception.FileUploadFailException;
import com.clab.securecoding.handler.FileHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class FileUploadService {

    private final FileHandler fileHandler;

    @Value("${resource.file.url}")
    private String fileURL;

    public String saveFile(MultipartFile multipartFile, String category) throws FileUploadFailException {
        String realFilename = fileHandler.saveFile(multipartFile, category);
        return fileURL + "/" + realFilename;
    }

}
