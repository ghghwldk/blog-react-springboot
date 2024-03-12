package com.m.blog.domain.loadfiletoawscloud.application.port.entrypoint.api;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileEndpointPort {
    ResponseEntity<String> upload(MultipartFile multipartFile, String typeDocument) throws IOException;

    List<ResponseEntity<String>> uploadMutipleFile(MultipartFile[] files, String type);

    ResponseEntity<ByteArrayResource> downloadFile(String fileName) throws IOException;
}
