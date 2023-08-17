package com.clab.securecoding.controller;

import com.clab.securecoding.exception.FileUploadFailException;
import com.clab.securecoding.service.FileUploadService;
import com.clab.securecoding.type.dto.ResponseModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
@Tag(name = "File")
@Slf4j
public class FileController {

    private final FileUploadService fileUploadService;

    @Operation(summary = "동물 사진 업로드", description = "동물 사진 업로드")
    @PostMapping("/animals/{path}")
    public ResponseModel animalsUpload(
            @PathVariable String path,
            @RequestParam(value = "files", required = true) MultipartFile[] multipartFiles
    ) throws FileUploadFailException {
        List<String> urls = fileUploadService.saveFiles(multipartFiles, "animals/" + path);
        ResponseModel responseModel = ResponseModel.builder().build();
        responseModel.addData(urls);
        return responseModel;
    }

    @Operation(summary = "스토어 물건 사진 업로드", description = "스토어 물건 사진 업로드")
    @PostMapping("/stores/{path}")
    public ResponseModel storesUpload(
            @PathVariable String path,
            @RequestParam(value = "files", required = true) MultipartFile[] multipartFiles
    ) throws FileUploadFailException {
        List<String> urls = fileUploadService.saveFiles(multipartFiles, "stores/" + path);
        ResponseModel responseModel = ResponseModel.builder().build();
        responseModel.addData(urls);
        return responseModel;
    }


}
