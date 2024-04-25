package com.m.blog.aggregate.file.application.port.persistence;

import com.m.blog.aggregate.file.application.domain.BlogFile;
import com.m.blog.aggregate.file.application.domain.BlogFile.DownloadTrialCondition;

import java.io.IOException;
import java.util.Optional;

public interface ReadFilePersistencePort {
    Optional<BlogFile> get(BlogFile.DownloadTrialCondition condition) throws IOException;
}
