package com.m.blog.domain.file.adapter.persistence;

import com.m.blog.common.Adapter;
import com.m.blog.domain.file.application.domain.DownloadFile;
import com.m.blog.domain.file.application.domain.File;
import com.m.blog.domain.file.infrastructure.repository.FileEntity;
import com.m.blog.domain.file.infrastructure.repository.FileJpaRepository;
import com.m.blog.domain.file.application.port.persistence.ReadFilePort;
import com.m.blog.domain.file.infrastructure.web.dto.FileDownloadRequest;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.Optional;


@Adapter
@RequiredArgsConstructor
public class ReadFileAdapter implements ReadFilePort {
    private final FileJpaRepository fileJpaRepository;

    @Override
    public DownloadFile getDownloadFile(FileDownloadRequest request) throws IOException {
        File file = fileJpaRepository.findByFileName(request.getFileName())
                .map(FileJpaMapper::toDomain)
                .orElseThrow(EntityNotFoundException::new);

        return DownloadFile.of(file, request);
    }
    @Override
    public File findByFileName(String fileName) {
        return fileJpaRepository.findByFileName(fileName)
                .map(FileJpaMapper::toDomain)
                .orElseThrow(EntityNotFoundException::new);
    }
}
