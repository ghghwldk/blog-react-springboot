package com.m.blog.domain.file.port.persistence;

import com.m.blog.domain.file.domain.File;
import com.m.blog.domain.file.domain.UploadFile;
import com.m.blog.domain.file.infrastructure.repository.FileEntity;

public interface WriteFilePort {
    File save(UploadFile uploadFile, String directoryName);
}
