package com.m.blog.domain.loadfiletoawscloud.application.port;


import com.m.blog.domain.loadfiletoawscloud.infrastructure.repository.FileEntity;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;

@Component
public interface FilePersistencePort {

    void saveFile(FileEntity file);

    FileEntity get(String fileName) throws FileNotFoundException;

    //boolean fileExiste(String fileName);
}
