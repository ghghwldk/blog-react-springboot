package com.m.blog.aggregate.file.infrastructure.file;

import java.util.List;

public interface FileDeleteUtil {
    void enterLocalDeleteQueue(List<String> fileKeys);
    void enterS3DeletingQueue(List<String> fileKeys);
}
