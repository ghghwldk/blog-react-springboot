package com.m.blog.domain.file.adapter.entrypoint.api;

import com.m.blog.common.Adapter;
import com.m.blog.domain.file.domain.File;
import com.m.blog.domain.file.port.entrypoint.api.FileDownloadPort;
import com.m.blog.domain.file.infrastructure.web.dto.FileDownloadRequest;
import com.m.blog.domain.file.infrastructure.file.FileDownloadHelper;
import com.m.blog.domain.file.domain.DownloadFile;
import com.m.blog.domain.file.port.persistence.ReadFilePort;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Adapter
@RequiredArgsConstructor
public class FileDownloadEndpointAdapter implements FileDownloadPort {
    private final ReadFilePort readFilePort;
    private final FileDownloadHelper fileDownloadHelper;


    @Value("${file.isLocal}")
    private boolean isLocal;

    private Resource getResource(DownloadFile fileVo) throws IOException {
        return isLocal?
                fileDownloadHelper.getLocalResource(fileVo):
                fileDownloadHelper.getS3Resource(fileVo);
    }

    private String getHeaderValues(DownloadFile fileVo) throws UnsupportedEncodingException {
        String encoded = URLEncoder.encode(fileVo.getOriginalName(), "UTF-8").replaceAll("\\+", "%20");
        return "attachment; filename=\"" + encoded + "\"";
    }

    @Override
    public ResponseEntity<Resource> get(FileDownloadRequest requestDto) throws IOException {
        File file = readFilePort.findByFileName(requestDto.getFileName());

        DownloadFile fileVo = DownloadFile.of(file, requestDto);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .header(HttpHeaders.CONTENT_DISPOSITION, getHeaderValues(fileVo))
                .body(this.getResource(fileVo));
    }
}
