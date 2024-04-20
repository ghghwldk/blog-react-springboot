package com.m.blog.aggregate.file.application.port.persistence;

import com.m.blog.aggregate.file.application.domain.UploadedFile;
import com.m.blog.aggregate.file.application.domain.File;

public interface WriteFilePersistencePort {
    void save(UploadedFile uploadedFile);
}
