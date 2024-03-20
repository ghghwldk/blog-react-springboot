package com.m.blog.domain.file.application.port.persistence;

import com.m.blog.domain.file.application.domain.File;

import java.io.IOException;

public interface ReadFilePersistencePort {
    File get(File.TrialCondition condition) throws IOException;

    File findByFileName(String fileName);
}
