package com.m.blog.domain.file.application.usecase;

import com.m.blog.domain.file.application.domain.DownloadCondition;
import com.m.blog.domain.file.application.domain.DownloadContent;

import java.io.IOException;

public interface FileDownloadUsecase {
    DownloadContent downlaod(DownloadCondition condition) throws IOException;
}
