package com.m.blog.domain.loadfiletoawscloud.infrastructure.web;

import com.m.blog.domain.loadfiletoawscloud.application.port.entrypoint.api.FileEndpointPort;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController()
@RequiredArgsConstructor
public class FileController2 {
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
