package com.m.blog.aggregate.file.application.service;

import com.m.blog.global.customAnnotation.Usecase;
import com.m.blog.aggregate.file.application.domain.File_;
import com.m.blog.aggregate.file.application.port.out.file.FileUploadPort;
import com.m.blog.aggregate.file.application.port.out.persistence.WriteFilePersistencePort;
import com.m.blog.aggregate.file.application.usecase.FileUploadUsecase;
import com.m.blog.global.properties.FileProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Usecase
@RequiredArgsConstructor
@Transactional
public class FileUploadService implements FileUploadUsecase {
    private final WriteFilePersistencePort writeFilePersistencePort;
    private final FileUploadPort fileUploadPort;
    private final FileProperties fileProperties;

    @Override
    public File_ upload(String postingId, MultipartFile multipartFile) throws IOException {
        File_ file = FileServiceMapper.of(postingId, multipartFile, fileProperties.getDirectoryName());

        file.setSnowflakeIdDuringUpload();
        writeFilePersistencePort.save(file);
        fileUploadPort.upload(file);

        return file;
    }
}
