package com.m.blog.aggregate.file.application.port.persistence;

import com.m.blog.aggregate.file.application.domain.BlogFile;

public interface WriteFilePersistencePort {
    void save(BlogFile blogFile);
}
