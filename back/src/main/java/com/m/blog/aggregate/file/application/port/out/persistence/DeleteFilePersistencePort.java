package com.m.blog.aggregate.file.application.port.out.persistence;

import com.m.blog.aggregate.file.application.domain.FileId;
import com.m.blog.aggregate.file.application.domain.File_;

import java.io.IOException;
import java.util.List;

public interface DeleteFilePersistencePort {
    void deleteAll(List<FileId> deleteTargets);
}
