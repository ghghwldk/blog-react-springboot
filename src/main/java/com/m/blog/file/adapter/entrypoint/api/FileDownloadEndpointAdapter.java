package com.m.blog.file.adapter.entrypoint.api;

import com.m.blog.common.Adapter;
import com.m.blog.file.application.domain.DownloadedFile;
import com.m.blog.file.application.domain.DownloadTrialCondition;
import com.m.blog.file.application.port.entrypoint.api.FileDownloadEndpointPort;
import com.m.blog.file.application.usecase.FileDownloadUsecase;
import com.m.blog.file.infrastructure.web.dto.FileDownloadRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Adapter
@RequiredArgsConstructor
public class FileDownloadEndpointAdapter implements FileDownloadEndpointPort {
    private final FileDownloadUsecase fileDownloadUsecase;

    @Override
    public ResponseEntity<Resource> download(FileDownloadRequest request) throws IOException {
        DownloadTrialCondition downloadTrialCondition = FileEntrypointMapper.of(request);
        DownloadedFile downloadedFile = fileDownloadUsecase.download(downloadTrialCondition);

        return get(downloadedFile);
    }

    private String getHeaderValues(String originalFileName) throws UnsupportedEncodingException {
        String encoded = URLEncoder.encode(originalFileName, StandardCharsets.UTF_8)
                .replaceAll("\\+", "%20");

        return "attachment; filename=\"" + encoded + "\"";
    }

    private ResponseEntity<Resource> get(DownloadedFile downloadedFile) throws UnsupportedEncodingException {
        Resource resource = new InputStreamResource(new ByteArrayInputStream(downloadedFile.getData()));
        String header = getHeaderValues(downloadedFile.getOriginalFileName());

        return get(resource, header);
    }

    private ResponseEntity<Resource> get(Resource resource, String header){
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(MediaType.APPLICATION_OCTET_STREAM_VALUE))
                .header(HttpHeaders.CONTENT_DISPOSITION, header)
                .body(resource);
    }
}
