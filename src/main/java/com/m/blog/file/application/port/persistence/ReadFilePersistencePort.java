package com.m.blog.file.application.port.persistence;

import com.m.blog.file.application.domain.File;
import com.m.blog.file.application.domain.DownloadTrialCondition;

import java.io.IOException;

public interface ReadFilePersistencePort {
    File get(DownloadTrialCondition condition) throws IOException;

    File findByFileName(String fileName);
}
