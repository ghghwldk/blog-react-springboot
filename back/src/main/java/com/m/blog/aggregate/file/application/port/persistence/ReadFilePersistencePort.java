package com.m.blog.aggregate.file.application.port.persistence;

import com.m.blog.aggregate.file.application.domain.BlogFile;

import java.io.IOException;
import java.util.Optional;

public interface ReadFilePersistencePort {
    Optional<BlogFile> get(BlogFile condition) throws IOException;
}
