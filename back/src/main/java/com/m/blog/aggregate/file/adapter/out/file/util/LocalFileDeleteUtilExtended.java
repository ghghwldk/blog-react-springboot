package com.m.blog.aggregate.file.adapter.out.file.util;

import com.m.blog.aggregate.file.adapter.out.file.util.FileDeleteUtil;
import com.m.blog.aggregate.file.application.domain.FileId;
import com.m.blog.aggregate.file.application.domain.File_;
import com.m.blog.global.properties.FileProperties;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class LocalFileDeleteUtilExtended extends FileDeleteUtil {
    private final FileProperties fileProperties;

    @Override
    protected void delete(List<FileId> fileIds){
        String directoryName = fileProperties.getDirectoryName();
        fileIds.stream()
                .map(fileId-> directoryName + "/" + fileId.getValue())
                .map(File::new)
                .collect(Collectors.toList())
                .forEach(FileUtils::deleteQuietly);
    }
}
