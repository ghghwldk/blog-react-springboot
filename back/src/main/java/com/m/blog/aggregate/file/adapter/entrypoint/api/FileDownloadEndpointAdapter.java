package com.m.blog.aggregate.file.adapter.entrypoint.api;

import com.m.blog.global.customAnnotation.Adapter;
import com.m.blog.aggregate.file.application.domain.BlogFile;
import com.m.blog.aggregate.file.application.domain.BlogFile.DownloadTrialCondition;
import com.m.blog.aggregate.file.application.port.entrypoint.api.FileDownloadEndpointPort;
import com.m.blog.aggregate.file.application.usecase.FileDownloadUsecase;
import com.m.blog.aggregate.file.infrastructure.web.dto.FileDownloadRequest;
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
        BlogFile.DownloadTrialCondition downloadTrialCondition = FileEntrypointMapper.of(request);
        BlogFile blogFile = fileDownloadUsecase.download(downloadTrialCondition);

        return get(blogFile);
    }

    private String getHeaderValues(String originalFileName) throws UnsupportedEncodingException {
        String encoded = URLEncoder.encode(originalFileName, StandardCharsets.UTF_8)
                .replaceAll("\\+", "%20");

        return "attachment; filename=\"" + encoded + "\"";
    }

    private ResponseEntity<Resource> get(BlogFile blogFile) throws UnsupportedEncodingException {
        Resource resource = new InputStreamResource(new ByteArrayInputStream(blogFile.getData()));
        String header = getHeaderValues(blogFile.getOriginalFileName());

        return get(resource, header);
    }

    private ResponseEntity<Resource> get(Resource resource, String header){
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(MediaType.APPLICATION_OCTET_STREAM_VALUE))
                .header(HttpHeaders.CONTENT_DISPOSITION, header)
                .body(resource);
    }
}
