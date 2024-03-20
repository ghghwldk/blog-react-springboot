package com.m.blog.domain.file.application.port.file;

import com.m.blog.domain.file.application.domain.DownloadedFile;
import com.m.blog.domain.file.application.domain.File;

import java.io.IOException;

public interface FileDownloadPort {

    DownloadedFile get(File fileVo) throws IOException;
}
