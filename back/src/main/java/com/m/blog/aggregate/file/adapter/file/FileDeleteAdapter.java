package com.m.blog.aggregate.file.adapter.file;

import com.m.blog.aggregate.file.application.domain.BaseFile;
import com.m.blog.aggregate.file.application.domain.BlogFile;
import com.m.blog.aggregate.file.application.usecase.FileDeleteUsecase;
import com.m.blog.global.customAnnotation.Adapter;
import com.m.blog.global.properties.FileProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Adapter
@RequiredArgsConstructor
@Slf4j
public class FileDeleteAdapter implements FileDeleteUsecase {
    private final FileProperties fileProperties;

    @Override
    public void delete(List<BaseFile.FileId> fileIds) {
        final String directoryName = fileProperties.getDirectoryName();
        List<String> fileKeys = fileIds.stream()
                .map(e->new BlogFile(e, directoryName))
                .map(BlogFile::getFileKey)
                .collect(Collectors.toList());

//        fileDeleteUtil.enterLocalDeleteQueue(fileKeys);
    }
}

