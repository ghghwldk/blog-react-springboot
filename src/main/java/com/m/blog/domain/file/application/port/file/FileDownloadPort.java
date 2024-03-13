package com.m.blog.domain.file.application.port.file;

import com.m.blog.domain.file.application.domain.DownloadContent;
import com.m.blog.domain.file.application.domain.DownloadFile;

import java.io.IOException;

public interface FileDownloadPort {

    DownloadContent get(DownloadFile fileVo) throws IOException;
}
