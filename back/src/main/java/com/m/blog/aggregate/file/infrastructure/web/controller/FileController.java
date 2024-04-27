package com.m.blog.aggregate.file.infrastructure.web.controller;

import com.m.blog.aggregate.file.application.domain.File_;
import com.m.blog.aggregate.file.infrastructure.web.dto.FileDownloadResponse;
import com.m.blog.aggregate.file.infrastructure.web.dto.FileUploadResponse;
import com.m.blog.aggregate.file.infrastructure.web.dto.FileDownloadRequest;
import com.m.blog.aggregate.file.infrastructure.web.dto.FileUploadRequest;
import com.m.blog.aggregate.file.application.port.in.web.FileDownloadEndpointPort;
import com.m.blog.aggregate.file.application.port.in.web.FileUploadEndpointPort;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RequestMapping("/file")
@RequiredArgsConstructor
@Controller
public class FileController {
    private final FileDownloadEndpointPort fileDownloadPort;
    private final FileUploadEndpointPort fileUploadPort;


    @PostMapping(value="/upload", produces = "application/json")
    @ResponseBody
    public ResponseEntity<FileUploadResponse> upload(@RequestParam("file") MultipartFile multipartFile
        , @RequestParam("postingId") String postingId
    ) throws IOException {
        FileUploadRequest request = FileUploadRequest.builder()
                .postingId(postingId)
                .multipartFile(multipartFile)
                .build();

        return ResponseEntity.ok(fileUploadPort.upload(request));
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> download(@PathVariable("id") String id) throws IOException{
        FileDownloadRequest request = FileDownloadRequest.builder()
                .id(id)
                .build();

        return toResponseEntity(fileDownloadPort.download(request));
    }

    private String getHeaderValues(String originalFileName) throws UnsupportedEncodingException {
        String encoded = URLEncoder.encode(originalFileName, StandardCharsets.UTF_8)
                .replaceAll("\\+", "%20");

        return "attachment; filename=\"" + encoded + "\"";
    }

    private ResponseEntity<Resource> toResponseEntity(FileDownloadResponse response) throws UnsupportedEncodingException {
        Resource resource = new InputStreamResource(new ByteArrayInputStream(response.getData()));
        String header = getHeaderValues(response.getOriginalFileName());

        return get(resource, header);
    }

    private ResponseEntity<Resource> get(Resource resource, String header){
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(MediaType.APPLICATION_OCTET_STREAM_VALUE))
                .header(HttpHeaders.CONTENT_DISPOSITION, header)
                .body(resource);
    }
}
