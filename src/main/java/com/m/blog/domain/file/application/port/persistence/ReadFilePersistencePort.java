package com.m.blog.domain.file.application.port.persistence;

import com.m.blog.domain.file.application.domain.BaseFile;

import java.io.IOException;

public interface ReadFilePersistencePort {
    BaseFile get(BaseFile.TrialCondition condition) throws IOException;

    BaseFile findByFileName(String fileName);
}
