package com.m.blog.domain.loadfiletoawscloud.application.port.persistence;


import com.m.blog.domain.loadfiletoawscloud.application.domain.File;
import com.m.blog.domain.loadfiletoawscloud.infrastructure.repository.FileEntity;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.util.Optional;

@Component
public interface FilePersistencePort {


    FileEntity get(String fileName) throws FileNotFoundException;

    String uploadFile(File file);

    Optional<File> downloadFile(String fileName) throws FileNotFoundException;

    //boolean fileExiste(String fileName);
}
