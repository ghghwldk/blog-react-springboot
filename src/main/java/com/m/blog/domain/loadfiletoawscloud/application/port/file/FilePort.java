package com.m.blog.domain.loadfiletoawscloud.application.port.file;

import com.m.blog.domain.loadfiletoawscloud.application.domain.File;
import com.m.blog.domain.loadfiletoawscloud.application.domain.FileUtils;

import java.io.InputStream;
import java.util.Optional;

public interface FilePort {
    InputStream getInputStream(File file);
    boolean fileExist(Optional<File> file);
    void uploadFile(File file);
}
