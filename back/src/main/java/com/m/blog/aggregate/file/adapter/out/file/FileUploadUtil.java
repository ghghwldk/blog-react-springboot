package com.m.blog.aggregate.file.adapter.out.file;

import java.io.IOException;

public interface FileUploadUtil {
    void upload(String fileId, String directoryName, byte[] data) throws IOException;
}
