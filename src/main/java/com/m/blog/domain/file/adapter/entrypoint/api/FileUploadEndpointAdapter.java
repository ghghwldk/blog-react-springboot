package com.m.blog.domain.file.adapter.entrypoint.api;

import com.m.blog.common.Adapter;
import com.m.blog.domain.file.port.entrypoint.api.FileUploadPort;
import com.m.blog.domain.file.infrastructure.web.dto.FileUploadRequest;
import com.m.blog.domain.file.infrastructure.web.dto.FileUploadResponse;
import com.m.blog.domain.file.infrastructure.file.FileUploadHelper;
import com.m.blog.domain.file.domain.UploadFile;
import com.m.blog.domain.file.port.persistence.WriteFilePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Adapter
public class FileUploadEndpointAdapter implements FileUploadPort {
    private final WriteFilePort writeFilePort;
    private final FileUploadHelper fileUploadHelper;

    @Value("${file.directory}") private String directoryName; // static
    @Value("${cloud.aws.s3.bucket:#{null}}") private String bucket;
    @Value("${file.isLocal}")
    private boolean isLocal;

    @Override
    public FileUploadResponse upload(FileUploadRequest requestDto) throws IOException{
        UploadFile uploadFile = UploadFile.of(requestDto.getMultipartFile());

        writeFilePort.save(uploadFile, this.directoryName);

        return FileUploadResponse.of(uploadFile);
    }

    private void upload(UploadFile fileVo) throws IOException {
        if(isLocal){
            fileUploadHelper.uploadOnLocal(fileVo);
        }else{
            fileUploadHelper.uploadOnS3(fileVo);
        }
    }
}
