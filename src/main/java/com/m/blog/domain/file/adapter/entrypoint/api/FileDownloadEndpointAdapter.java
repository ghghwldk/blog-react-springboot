package com.m.blog.domain.file.adapter.entrypoint.api;

import com.m.blog.common.Adapter;
import com.m.blog.domain.file.application.domain.DownloadResult;
import com.m.blog.domain.file.application.domain.BaseFile;
import com.m.blog.domain.file.application.domain.DownloadTrialCondition;
import com.m.blog.domain.file.application.port.entrypoint.api.FileDownloadEndpointPort;
import com.m.blog.domain.file.application.usecase.FileDownloadUsecase;
import com.m.blog.domain.file.infrastructure.web.dto.FileDownloadRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

@Adapter
@RequiredArgsConstructor
public class FileDownloadEndpointAdapter implements FileDownloadEndpointPort {
    private final FileDownloadUsecase fileDownloadUsecase;

    private ResponseEntity<Resource> get(DownloadResult downloadResult) throws UnsupportedEncodingException {
        Resource resource = new InputStreamResource(downloadResult.getData());
        String header = downloadResult.getBaseFile()
                .getHeaderValues();

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .header(HttpHeaders.CONTENT_DISPOSITION, header)
                .body(resource);
    }

    @Override
    public ResponseEntity<Resource> download(FileDownloadRequest request) throws IOException {
        DownloadTrialCondition downloadTrialCondition = FileMapper.of(request);

        DownloadResult downloadResult = fileDownloadUsecase.download(downloadTrialCondition);

        return get(downloadResult);
    }
}
