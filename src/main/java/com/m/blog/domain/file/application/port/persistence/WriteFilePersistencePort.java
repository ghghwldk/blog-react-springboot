package com.m.blog.domain.file.application.port.persistence;

import com.m.blog.domain.file.application.domain.UploadFile;
import com.m.blog.domain.file.application.domain.BaseFile;

public interface WriteFilePersistencePort {
    BaseFile save(UploadFile uploadFile);
}
