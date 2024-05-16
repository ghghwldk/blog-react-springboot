package com.m.blog.aggregate.file.application.port.in.web;

import com.m.blog.aggregate.file.adapter.in.web.FileUploadRequest;
import com.m.blog.aggregate.file.adapter.in.web.FileUploadResponse;

import java.io.IOException;

public interface FileUploadEndpointPort {
    FileUploadResponse upload(FileUploadRequest request) throws IOException;
}
