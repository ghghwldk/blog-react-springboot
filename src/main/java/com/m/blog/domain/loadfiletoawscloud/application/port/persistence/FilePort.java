package com.m.blog.domain.loadfiletoawscloud.application.port.persistence;


import com.m.blog.domain.loadfiletoawscloud.application.domain.File;

import java.io.FileNotFoundException;
import java.util.Optional;

public interface FilePort {
    
    Optional<File> downloadFile(String fileName) throws FileNotFoundException;

    //boolean fileExiste(String fileName);
}
