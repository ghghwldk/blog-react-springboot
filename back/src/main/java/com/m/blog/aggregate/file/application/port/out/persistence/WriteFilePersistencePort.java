package com.m.blog.aggregate.file.application.port.out.persistence;

import com.m.blog.aggregate.file.application.domain.File_;

public interface WriteFilePersistencePort {
    void save(File_ file);
}
