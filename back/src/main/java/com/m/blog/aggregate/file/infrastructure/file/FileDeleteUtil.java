package com.m.blog.aggregate.file.infrastructure.file;

import java.util.List;

public interface FileDeleteUtil {
    void enterQueue(List<String> fileKeys);
}
