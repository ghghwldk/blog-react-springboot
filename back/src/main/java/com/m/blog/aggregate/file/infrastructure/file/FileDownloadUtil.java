package com.m.blog.aggregate.file.infrastructure.file;

import com.m.blog.aggregate.file.application.domain.BlogFile;

import java.io.IOException;
import java.io.InputStream;

public interface FileDownloadUtil {
    InputStream get(BlogFile info) throws IOException;

//    InputStream getLocalResource(BlogFile info) throws IOException;
}
