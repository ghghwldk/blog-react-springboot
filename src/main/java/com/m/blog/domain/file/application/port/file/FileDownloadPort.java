package com.m.blog.domain.file.application.port.file;

import com.m.blog.domain.file.application.domain.DownloadContent;
import com.m.blog.domain.file.application.domain.DownloadFileInfo;

import java.io.IOException;

public interface FileDownloadPort {

    DownloadContent get(DownloadFileInfo fileVo) throws IOException;
}
