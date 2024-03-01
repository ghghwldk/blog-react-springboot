package com.m.blog.domain.file.service;

import com.m.blog.domain.file.adapter.entrypoint.api.FileDownloadPort;
import com.m.blog.domain.file.infrastructure.repository.FileEntity;
import com.m.blog.domain.file.infrastructure.web.dto.FileDownloadRequest;
import com.m.blog.domain.file.infrastructure.repository.FileJpaRepository;
import com.m.blog.domain.file.infrastructure.file.FileDownload;
import com.m.blog.domain.file.infrastructure.file.DownloadFileVo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Service
@RequiredArgsConstructor
public class FileDownloadServiceImpl implements FileDownloadPort {
    @Autowired
    FileJpaRepository fileJpaRepository;
    private final FileDownload fileDownloadUtil;


    @Value("${file.isLocal}")
    private boolean isLocal;

    private Resource getResource(DownloadFileVo fileVo) throws IOException {
        return isLocal?
                fileDownloadUtil.getLocalResource(fileVo):
                fileDownloadUtil.getS3Resource(fileVo);
    }

    private String getHeaderValues(DownloadFileVo fileVo) throws UnsupportedEncodingException {
        String encoded = URLEncoder.encode(fileVo.getOriginalName(), "UTF-8").replaceAll("\\+", "%20");
        return "attachment; filename=\"" + encoded + "\"";
    }

    @Override
    public ResponseEntity<Resource> get(FileDownloadRequest requestDto) throws IOException {
        FileEntity file = fileJpaRepository.findByFileName(requestDto.getFileName())
                .orElseThrow(RuntimeException::new);

        DownloadFileVo fileVo = DownloadFileVo.of(file, requestDto);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .header(HttpHeaders.CONTENT_DISPOSITION, getHeaderValues(fileVo))
                .body(this.getResource(fileVo));
    }
}
