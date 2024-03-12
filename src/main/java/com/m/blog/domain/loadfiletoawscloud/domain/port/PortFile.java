package com.m.blog.domain.loadfiletoawscloud.domain.port;


import com.m.blog.domain.loadfiletoawscloud.domain.model.File;

import java.io.FileNotFoundException;
import java.util.Optional;

public interface PortFile {
    String uploadFile(File file);

    Optional<File> downloadFile(String fileName) throws FileNotFoundException;

    //boolean fileExiste(String fileName);
}
