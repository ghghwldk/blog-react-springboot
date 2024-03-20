package com.m.blog.domain.file.application.usecase;

import com.m.blog.domain.file.application.domain.DownloadedFile;
import com.m.blog.domain.file.application.domain.DownloadTrialCondition;

import java.io.IOException;

public interface FileDownloadUsecase {
    DownloadedFile download(DownloadTrialCondition condition) throws IOException;
}
