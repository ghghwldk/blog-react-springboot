package com.m.blog.file.application.port.file;

import com.m.blog.file.application.domain.DownloadedFile;
import com.m.blog.file.application.domain.File;

import java.io.IOException;

public interface FileDownloadPort {

    DownloadedFile get(File fileVo) throws IOException;
}
