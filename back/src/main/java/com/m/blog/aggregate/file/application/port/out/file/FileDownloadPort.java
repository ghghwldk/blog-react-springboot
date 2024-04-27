package com.m.blog.aggregate.file.application.port.out.file;

import com.m.blog.aggregate.file.application.domain.BlogFile;
import com.m.blog.aggregate.file.application.domain.BlogFile;

import java.io.IOException;

public interface FileDownloadPort {

    BlogFile get(BlogFile blogFileVo) throws IOException;
}
