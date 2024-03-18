package com.m.blog.domain.file.application.usecase;

import com.m.blog.domain.file.application.domain.DownloadFile;
import com.m.blog.domain.file.application.domain.DownloadResult;

import java.io.IOException;

public interface FileDownloadUsecase {
    DownloadResult download(DownloadFile.TrialCondition condition) throws IOException;
}
