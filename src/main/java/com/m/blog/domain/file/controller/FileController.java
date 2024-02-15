package com.m.blog.domain.file;

import com.google.gson.JsonObject;
import com.m.blog.domain.file.dto.FileDownloadRequestDto;
import com.m.blog.domain.file.dto.FileUploadRequestDto;
import com.m.blog.domain.file.dto.FileUploadResponseDto;
import com.m.blog.domain.file.service.FileDownloadService;
import com.m.blog.domain.file.service.FileDownloadServiceImpl;
import com.m.blog.domain.file.service.FileUploadService;
import com.m.blog.domain.file.service.FileUploadServiceImpl;
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
    public FileUploadResponseDto upload(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        return fileUploadService.upload(FileUploadRequestDto.builder()
                .multipartFile(multipartFile)
                .build());
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<Resource> download(@PathVariable("fileName") String fileName) throws IOException{
        return fileDownloadService.get(FileDownloadRequestDto.builder()
                .fileName(fileName)
                .build());
    }
}
