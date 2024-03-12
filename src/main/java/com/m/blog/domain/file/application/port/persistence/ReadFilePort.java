package com.m.blog.domain.file.application.port.persistence;

import com.m.blog.domain.file.application.domain.DownloadCondition;
import com.m.blog.domain.file.application.domain.DownloadFileInfo;
import com.m.blog.domain.file.application.domain.File;
import com.m.blog.domain.file.infrastructure.web.dto.FileDownloadRequest;

import java.io.IOException;

public interface ReadFilePort {
    DownloadFileInfo get(DownloadCondition condition) throws IOException;

    File findByFileName(String fileName);
}
