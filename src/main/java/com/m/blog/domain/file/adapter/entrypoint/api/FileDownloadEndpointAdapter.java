package com.m.blog.domain.file.adapter.entrypoint.api;

import com.m.blog.common.Adapter;
import com.m.blog.domain.file.application.domain.File;
import com.m.blog.domain.file.application.port.entrypoint.api.FileDownloadEndpointPort;
import com.m.blog.domain.file.application.port.file.FileDownloadPort;
import com.m.blog.domain.file.infrastructure.web.dto.FileDownloadRequest;
import com.m.blog.domain.file.infrastructure.file.FileDownloadHelper;
import com.m.blog.domain.file.application.domain.DownloadFile;
import com.m.blog.domain.file.application.port.persistence.ReadFilePort;
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
public class FileDownloadEndpointAdapter implements FileDownloadEndpointPort {
    private final FileDownloadPort fileDownloadPort;
    private final ReadFilePort readFilePort;

    private String getHeaderValues(DownloadFile fileVo) throws UnsupportedEncodingException {
        String encoded = URLEncoder.encode(fileVo.getOriginalName(), "UTF-8").replaceAll("\\+", "%20");
        return "attachment; filename=\"" + encoded + "\"";
    }

    @Override
    public ResponseEntity<Resource> getResponse(FileDownloadRequest request) throws IOException {
        DownloadFile downloadFile = readFilePort.getDownloadFile(request);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .header(HttpHeaders.CONTENT_DISPOSITION, getHeaderValues(downloadFile))
                .body(fileDownloadPort.getResource(downloadFile));
    }
}
