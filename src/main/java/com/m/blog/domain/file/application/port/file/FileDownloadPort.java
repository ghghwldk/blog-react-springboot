package com.m.blog.domain.file.application.port.file;

import com.m.blog.domain.file.application.domain.DownloadResult;
import com.m.blog.domain.file.application.domain.BaseFile;

import java.io.IOException;

public interface FileDownloadPort {

    DownloadResult get(BaseFile baseFileVo) throws IOException;
}
