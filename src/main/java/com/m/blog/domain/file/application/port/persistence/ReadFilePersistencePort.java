package com.m.blog.domain.file.application.port.persistence;

import com.m.blog.domain.file.application.domain.File;
import com.m.blog.domain.file.application.domain.DownloadTrialCondition;

import java.io.IOException;

public interface ReadFilePersistencePort {
    File get(DownloadTrialCondition condition) throws IOException;

    File findByFileName(String fileName);
}
