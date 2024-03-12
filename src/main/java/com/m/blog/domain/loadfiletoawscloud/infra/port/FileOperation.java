package com.m.blog.domain.loadfiletoawscloud.infra.port;


import com.m.blog.domain.loadfiletoawscloud.infra.entity.FileEntity;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;

@Component
public interface FileOperation {

    void saveFile(FileEntity file);

    FileEntity get(String fileName) throws FileNotFoundException;

    //boolean fileExiste(String fileName);
}
