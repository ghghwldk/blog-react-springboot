package com.m.blog.domain.file.application.port.persistence;

import com.m.blog.domain.file.application.domain.DownloadFile;
import com.m.blog.domain.file.application.domain.File;
import com.m.blog.domain.file.infrastructure.web.dto.FileDownloadRequest;

import java.io.IOException;

public interface ReadFilePort {
    DownloadFile getDownloadFile(FileDownloadRequest request) throws IOException;

    File findByFileName(String fileName);
}
