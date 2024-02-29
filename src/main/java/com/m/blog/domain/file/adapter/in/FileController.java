package com.m.blog.domain.file.adapter.in;

import com.m.blog.domain.file.adapter.in.FileDownloadRequest;
import com.m.blog.domain.file.adapter.in.FileUploadRequest;
import com.m.blog.domain.file.adapter.out.FileUploadResponse;
import com.m.blog.domain.file.service.FileDownloadService;
import com.m.blog.domain.file.service.FileUploadService;
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
    private final FileDownloadService fileDownloadService;
    private final FileUploadService fileUploadService;


    @PostMapping(value="/upload", produces = "application/json")
    @ResponseBody
    public FileUploadResponse upload(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        return fileUploadService.upload(FileUploadRequest.builder()
                .multipartFile(multipartFile)
                .build());
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<Resource> download(@PathVariable("fileName") String fileName) throws IOException{
        return fileDownloadService.get(FileDownloadRequest.builder()
                .fileName(fileName)
                .build());
    }
}
