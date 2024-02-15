package com.m.blog.domain.file.service;

import com.amazonaws.services.s3.AmazonS3;
import com.m.blog.domain.file.dto.FileDownloadRequestDto;
import com.m.blog.domain.file.entity.File;
import com.m.blog.domain.file.repository.FileJpaRepository;
import com.m.blog.domain.file.util.FileDownloadUtil;
import com.m.blog.domain.file.vo.DownloadFileVo;
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
public class FileDownloadServiceImpl implements FileDownloadService{
    @Autowired
    FileJpaRepository fileJpaRepository;
    private final FileDownloadUtil fileDownloadUtil;


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
    public ResponseEntity<Resource> get(FileDownloadRequestDto requestDto) throws IOException {
        File file = fileJpaRepository.findByFileName(requestDto.getFileName())
                .orElseThrow(RuntimeException::new);

        DownloadFileVo fileVo = DownloadFileVo.of(file, requestDto);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .header(HttpHeaders.CONTENT_DISPOSITION, getHeaderValues(fileVo))
                .body(this.getResource(fileVo));
    }
}
