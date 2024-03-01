package com.m.blog.domain.file.infrastructure.web.controller;

import com.m.blog.domain.file.infrastructure.web.dto.FileUploadResponse;
import com.m.blog.domain.file.infrastructure.web.dto.FileDownloadRequest;
import com.m.blog.domain.file.infrastructure.web.dto.FileUploadRequest;
import com.m.blog.domain.file.application.port.entrypoint.api.FileDownloadEndpointPort;
import com.m.blog.domain.file.application.port.entrypoint.api.FileUploadEndpointPort;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequestMapping("/file")
@RequiredArgsConstructor
@Controller
public class FileController {
    private final FileDownloadEndpointPort fileDownloadPort;
    private final FileUploadEndpointPort fileUploadPort;


    @PostMapping(value="/upload", produces = "application/json")
    @ResponseBody
    public FileUploadResponse upload(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        return fileUploadPort.upload(FileUploadRequest.builder()
                .multipartFile(multipartFile)
                .build());
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<Resource> download(@PathVariable("fileName") String fileName) throws IOException{
        return fileDownloadPort.getResponse(FileDownloadRequest.builder()
                .fileName(fileName)
                .build());
    }
}
