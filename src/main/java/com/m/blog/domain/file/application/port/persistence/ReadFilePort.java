package com.m.blog.domain.file.application.port.persistence;

import com.m.blog.domain.file.application.domain.DownloadFile;
import com.m.blog.domain.file.application.domain.File;

import java.io.IOException;

public interface ReadFilePort {
    DownloadFile get(DownloadFile.TrialCondition condition) throws IOException;

    File findByFileName(String fileName);
}
