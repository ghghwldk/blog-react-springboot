package com.m.blog.aggregate.file.application.port.out.persistence;

import com.m.blog.aggregate.file.application.domain.FileId;
import com.m.blog.aggregate.file.application.domain.File_;
import com.m.blog.aggregate.posting.application.domain.Posting;
import com.m.blog.aggregate.posting.application.domain.PostingId;

import java.util.List;
import java.util.Optional;

public interface ReadFilePersistencePort {
    Optional<File_> find(String fileId);
    List<FileId> findAll(PostingId postingId);
}
