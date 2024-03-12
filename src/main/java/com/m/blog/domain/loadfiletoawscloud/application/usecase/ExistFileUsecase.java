package com.m.blog.domain.loadfiletoawscloud.application.usecase;

import com.m.blog.domain.loadfiletoawscloud.application.domain.File;
import com.m.blog.domain.loadfiletoawscloud.application.port.file.FilePort;
import com.m.blog.domain.loadfiletoawscloud.application.port.persistence.FilePersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ExistFileUsecase {

    private final FilePersistencePort filePersistencePort;
    private final FilePort filePort;



    public boolean excute(String fileName){
       try {
           Optional<File> file = filePersistencePort.downloadFile(fileName);
            if(file.isPresent()){
                return file.get().getName().equalsIgnoreCase(fileName) && filePort.fileExist(file);
            }
            return true;
       } catch (FileNotFoundException e) {
            return true;
        }
    }
}
