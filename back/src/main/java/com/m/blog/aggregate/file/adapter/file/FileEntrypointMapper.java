package com.m.blog.aggregate.file.adapter.file;

import com.m.blog.aggregate.file.application.domain.BlogFile;
import com.m.blog.aggregate.file.application.domain.BlogFile;
import com.m.blog.global.customAnnotation.Mapper;

@Mapper
public class FileEntrypointMapper {
    public static BlogFile from(byte[] data, BlogFile blogFile){
        return BlogFile.builder()
                .fileId(blogFile.getFileId())
                .originalFileName(blogFile.getOriginalFileName())
                .directoryName(blogFile.getDirectoryName())
                .data(data)
                .build();
    }
}
