package com.m.blog.domain.file.application.port.file;

import com.m.blog.domain.file.application.domain.DownloadResult;
import com.m.blog.domain.file.application.domain.DownloadFile;

import java.io.IOException;

public interface FileDownloadPort {

    DownloadResult get(DownloadFile fileVo) throws IOException;
}
