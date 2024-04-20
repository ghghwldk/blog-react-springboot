package com.m.blog.aggregate.file.application.port.file;

import com.m.blog.aggregate.file.application.domain.DownloadedFile;
import com.m.blog.aggregate.file.application.domain.File;

import java.io.IOException;

public interface FileDownloadPort {

    DownloadedFile get(File fileVo) throws IOException;
}
