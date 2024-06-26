package com.m.blog.aggregate.file.adapter.out.file.util;

import com.m.blog.aggregate.file.adapter.out.file.util.FileDownloadUtil;
import com.m.blog.aggregate.file.application.domain.File_;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LocalFileDownloadUtilImpl implements FileDownloadUtil {

    @Override
    public InputStream get(File_ file) throws IOException {
        Path path = Paths.get(file.getPathName());

        return Files.newInputStream(path);
    }
}
