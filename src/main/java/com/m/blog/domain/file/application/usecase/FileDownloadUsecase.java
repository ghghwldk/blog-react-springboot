package com.m.blog.domain.file.application.usecase;

import com.m.blog.domain.file.application.domain.DownloadResult;
import com.m.blog.domain.file.application.domain.BaseFile;

import java.io.IOException;

public interface FileDownloadUsecase {
    DownloadResult download(BaseFile.TrialCondition condition) throws IOException;
}
