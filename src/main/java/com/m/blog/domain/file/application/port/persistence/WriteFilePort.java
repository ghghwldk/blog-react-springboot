package com.m.blog.domain.file.application.port.persistence;

import com.m.blog.domain.file.application.domain.UploadFile;
import com.m.blog.domain.file.application.domain.File;

public interface WriteFilePort {
    File save(UploadFile uploadFile);
}
