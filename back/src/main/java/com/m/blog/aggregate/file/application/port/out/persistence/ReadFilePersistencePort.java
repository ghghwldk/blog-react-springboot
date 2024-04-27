package com.m.blog.aggregate.file.application.port.out.persistence;

import com.m.blog.aggregate.file.application.domain.File_;

import java.io.IOException;
import java.util.Optional;

public interface ReadFilePersistencePort {
    Optional<File_> get(File_ condition) throws IOException;
}
