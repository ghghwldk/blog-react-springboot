package com.m.blog.domain.file.application.usecase;

import com.m.blog.domain.file.application.domain.DownloadResult;
import com.m.blog.domain.file.application.domain.File;

import java.io.IOException;

public interface FileDownloadUsecase {
    DownloadResult download(File.TrialCondition condition) throws IOException;
}
