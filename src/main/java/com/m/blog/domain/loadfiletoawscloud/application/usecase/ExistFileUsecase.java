package com.m.blog.domain.loadfiletoawscloud.application.usecase;

import com.m.blog.domain.loadfiletoawscloud.application.domain.File;
import com.m.blog.domain.loadfiletoawscloud.application.port.persistence.FilePersistencePort;
import com.m.blog.domain.loadfiletoawscloud.infrastructure.aws.AmazonClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ExistFileUsecase {

    private final FilePersistencePort filePersistencePort;

    private final AmazonClient amazonClient;


    public boolean excute(String fileName){
       try {
           Optional<File> file = filePersistencePort.downloadFile(fileName);
            if(file.isPresent()){
                return file.get().getName().equalsIgnoreCase(fileName) &&
                        amazonClient.fileExistInBucket(file.get().getFileNature()+"/"+file.get().getUuidAwsFile());
            }
            return true;
       } catch (FileNotFoundException e) {
            return true;
        }
    }
}
