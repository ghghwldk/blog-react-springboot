package com.m.blog.aggregate.file.adapter.in.web;

import com.m.blog.aggregate.file.application.domain.File_;
import com.m.blog.aggregate.file.application.usecase.FileDownloadUsecase;
import com.m.blog.aggregate.file.application.usecase.FileUploadUsecase;
import com.m.blog.global.exception.GetFileNullException;
import com.m.blog.global.properties.FileProperties;
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

@RequestMapping("/api/file")
@RequiredArgsConstructor
@Controller
public class FileController {
    private final FileDownloadUsecase fileDownloadUsecase;
    private final FileUploadUsecase fileUploadUsecase;

    @PostMapping(value="/upload", produces = "application/json")
    @ResponseBody
    public ResponseEntity<FileUploadResponse> upload(@RequestParam("file") MultipartFile multipartFile
        , @RequestParam("postingId") String postingId
    ) throws IOException {
        FileUploadResponse response = this.upload(postingId, multipartFile);
        return ResponseEntity.ok(response);
    }


    public FileUploadResponse upload(String postingId, MultipartFile multipartFile) throws IOException{
         File_ file = fileUploadUsecase.upload(postingId, multipartFile);

        return FileEntrypointMapper.toUploadResponse(file);
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> download(@PathVariable("id") String id) throws IOException{
        File_ file = fileDownloadUsecase.download(id);

        return toResponseEntity(file);
    }

    private String getHeaderValues(String originalFileName) throws UnsupportedEncodingException {
        String encoded = URLEncoder.encode(originalFileName, StandardCharsets.UTF_8)
                .replaceAll("\\+", "%20");

        return "attachment; filename=\"" + encoded + "\"";
    }

    private ResponseEntity<Resource> toResponseEntity(File_ file) throws UnsupportedEncodingException {
        Resource resource = new InputStreamResource(new ByteArrayInputStream(file
                .getDownloadData()
                .orElseThrow(GetFileNullException::new)));
        String header = getHeaderValues(file.getOriginalFileName());

        return get(resource, header);
    }

    private ResponseEntity<Resource> get(Resource resource, String header){
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(MediaType.APPLICATION_OCTET_STREAM_VALUE))
                .header(HttpHeaders.CONTENT_DISPOSITION, header)
                .body(resource);
    }
}
