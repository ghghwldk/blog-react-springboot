package com.m.blog.domain.loadfiletoawscloud.infrastructure.web;

import com.m.blog.domain.loadfiletoawscloud.application.domain.FileMapper;
import com.m.blog.domain.loadfiletoawscloud.application.domain.File;
import com.m.blog.domain.loadfiletoawscloud.application.port.entrypoint.api.FileEndpointPort;
import com.m.blog.domain.loadfiletoawscloud.application.usecase.DownloadFileUsecase;
import com.m.blog.domain.loadfiletoawscloud.application.usecase.ExistFileUsecase;
import com.m.blog.domain.loadfiletoawscloud.application.usecase.UploadFileUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@RestController()
@RequiredArgsConstructor
public class FileController {
    private FileEndpointPort fileEndpointPort;

    @PostMapping("/uploadFile")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile multipartFile, @RequestParam("type") String typeDocument) throws IOException {
        return fileEndpointPort.upload(multipartFile, typeDocument);
    }


    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String fileName) throws IOException {
        return fileEndpointPort.downloadFile(fileName);
    }
}
