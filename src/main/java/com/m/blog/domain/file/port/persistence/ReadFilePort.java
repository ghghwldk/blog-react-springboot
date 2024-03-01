package com.m.blog.domain.file.port.persistence;

import com.m.blog.domain.file.domain.File;
import com.m.blog.domain.file.infrastructure.repository.FileEntity;

import java.util.Optional;

public interface ReadFilePort {
    File findByFileName(String fileName);
}
