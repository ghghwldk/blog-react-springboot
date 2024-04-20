package com.m.blog.file.application.port.persistence;

import com.m.blog.file.application.domain.UploadedFile;
import com.m.blog.file.application.domain.File;

public interface WriteFilePersistencePort {
    void save(UploadedFile uploadedFile);
}
