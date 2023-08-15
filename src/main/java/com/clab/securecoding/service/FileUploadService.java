package com.clab.securecoding.service;

import com.clab.securecoding.exception.FileUploadFailException;
import com.clab.securecoding.handler.FileHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FileUploadService {

    private final FileHandler fileHandler;

    @Value("${resource.file.url}")
    private String fileURL;

    public List<String> saveFiles(MultipartFile[] multipartFiles, String path) throws FileUploadFailException {
        List<String> urls = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            String url = saveFile(multipartFile, path);
            urls.add(url);
        }
        return urls;
    }

    public String saveFile(MultipartFile multipartFile, String path) throws FileUploadFailException {
        String realFilename = fileHandler.saveFile(multipartFile, path);
        return fileURL + "/" + realFilename;
    }

}
