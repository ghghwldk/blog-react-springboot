package com.m.blog.aggregate.file.application.port.in.web;

import com.m.blog.aggregate.file.adapter.in.web.FileDownloadRequest;
import com.m.blog.aggregate.file.adapter.in.web.FileDownloadResponse;

import java.io.IOException;

public interface FileDownloadEndpointPort {
    FileDownloadResponse download(FileDownloadRequest request) throws IOException;
}
