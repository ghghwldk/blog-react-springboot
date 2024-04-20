package com.m.blog.aggregate.file.application.port.persistence;

import com.m.blog.aggregate.file.application.domain.File;
import com.m.blog.aggregate.file.application.domain.DownloadTrialCondition;

import javax.swing.text.html.Option;
import java.io.IOException;
import java.util.Optional;

public interface ReadFilePersistencePort {
    Optional<File> get(DownloadTrialCondition condition) throws IOException;

    Optional<File> findByFileName(String fileName);
}
