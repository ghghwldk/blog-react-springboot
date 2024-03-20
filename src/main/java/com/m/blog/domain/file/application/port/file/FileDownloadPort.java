package com.m.blog.domain.file.application.port.file;

import com.m.blog.domain.file.application.domain.DownloadResult;
import com.m.blog.domain.file.application.domain.File;

import java.io.IOException;

public interface FileDownloadPort {

    DownloadResult get(File fileVo) throws IOException;
}
