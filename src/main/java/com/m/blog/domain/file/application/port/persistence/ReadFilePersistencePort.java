package com.m.blog.domain.file.application.port.persistence;

import com.m.blog.domain.file.application.domain.BaseFile;
import com.m.blog.domain.file.application.domain.DownloadTrialCondition;

import java.io.IOException;

public interface ReadFilePersistencePort {
    BaseFile get(DownloadTrialCondition condition) throws IOException;

    BaseFile findByFileName(String fileName);
}
