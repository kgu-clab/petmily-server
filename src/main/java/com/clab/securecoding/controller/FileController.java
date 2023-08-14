package com.clab.securecoding.controller;

import com.clab.securecoding.exception.FileUploadFailException;
import com.clab.securecoding.service.FileUploadService;
import com.clab.securecoding.type.dto.ResponseModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
@Tag(name = "File")
@Slf4j
public class FileController {

    private final FileUploadService fileUploadService;

    @Operation(summary = "동물 사진 업로드", description = "동물 사진 업로드")
    @PostMapping("/animals")
    public ResponseModel animalsUpload(
            @RequestParam(value = "file", required = true) MultipartFile multipartFile
    ) throws FileUploadFailException {
        String url = fileUploadService.saveFile(multipartFile, "animal");
        ResponseModel responseModel = ResponseModel.builder().build();
        responseModel.addData(url);
        return responseModel;
    }

    @Operation(summary = "스토어 물건 사진 업로드", description = "스토어 물건 사진 업로드")
    @PostMapping("/stores")
    public ResponseModel storesUpload(
            @RequestParam(value = "file", required = true) MultipartFile multipartFile
    ) throws FileUploadFailException {
        String url = fileUploadService.saveFile(multipartFile, "store");
        ResponseModel responseModel = ResponseModel.builder().build();
        responseModel.addData(url);
        return responseModel;
    }

}
