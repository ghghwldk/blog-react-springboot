package com.m.blog.aggregate.file.application.port.out.file;

import com.m.blog.aggregate.file.application.domain.File_;

import java.io.IOException;

public interface FileDownloadPort {

    File_ get(File_ fileVo) throws IOException;
}
