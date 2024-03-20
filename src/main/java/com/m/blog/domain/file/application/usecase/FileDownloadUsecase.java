package com.m.blog.domain.file.application.usecase;

import com.m.blog.domain.file.application.domain.DownloadResult;
import com.m.blog.domain.file.application.domain.BaseFile;
import com.m.blog.domain.file.application.domain.DownloadTrialCondition;

import java.io.IOException;

public interface FileDownloadUsecase {
    DownloadResult download(DownloadTrialCondition condition) throws IOException;
}
