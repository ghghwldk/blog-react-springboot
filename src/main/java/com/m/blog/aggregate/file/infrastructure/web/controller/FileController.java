package com.m.blog.aggregate.file.infrastructure.web.controller;

import com.m.blog.aggregate.file.infrastructure.web.dto.FileUploadResponse;
import com.m.blog.aggregate.file.infrastructure.web.dto.FileDownloadRequest;
import com.m.blog.aggregate.file.infrastructure.web.dto.FileUploadRequest;
import com.m.blog.aggregate.file.application.port.entrypoint.api.FileDownloadEndpointPort;
import com.m.blog.aggregate.file.application.port.entrypoint.api.FileUploadEndpointPort;
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
    public FileUploadResponse upload(@RequestParam("file") MultipartFile multipartFile
        , @RequestParam("postingId") String postingId
    ) throws IOException {
        return fileUploadPort.upload(FileUploadRequest.builder()
                .postingId(postingId)
                .multipartFile(multipartFile)
                .build());
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> download(@PathVariable("id") String id) throws IOException{
        return fileDownloadPort.download(FileDownloadRequest.builder()
                .id(id)
                .build());
    }
}