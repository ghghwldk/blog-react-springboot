package com.m.blog.aggregate.file.adapter.out.file.util;

import com.m.blog.aggregate.file.application.domain.File_;

import java.io.IOException;
import java.io.InputStream;

public interface FileDownloadUtil {
    InputStream get(File_ info) throws IOException;
}
