package com.m.blog.aggregate.file.application.usecase;

import com.m.blog.aggregate.file.application.domain.BlogFile;
import com.m.blog.aggregate.file.application.domain.BlogFile.DownloadTrialCondition;

import java.io.IOException;

public interface FileDownloadUsecase {
    BlogFile download(BlogFile.DownloadTrialCondition condition) throws IOException;
}
