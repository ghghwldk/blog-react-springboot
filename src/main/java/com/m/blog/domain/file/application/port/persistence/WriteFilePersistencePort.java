package com.m.blog.domain.file.application.port.persistence;

import com.m.blog.domain.file.application.domain.UploadedFile;
import com.m.blog.domain.file.application.domain.File;

public interface WriteFilePersistencePort {
    File save(UploadedFile uploadedFile);
}
