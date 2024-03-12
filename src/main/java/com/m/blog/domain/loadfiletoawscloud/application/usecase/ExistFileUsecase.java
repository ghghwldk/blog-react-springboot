package com.m.blog.domain.loadfiletoawscloud.application.usecase;

import com.m.blog.domain.loadfiletoawscloud.application.domain.File;
import com.m.blog.domain.loadfiletoawscloud.application.port.FilePort;
import com.m.blog.domain.loadfiletoawscloud.infrastructure.aws.AmazonClient;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.util.Optional;

@Component
public class ExistFileUsecase {

    private final FilePort filePort;

    private final AmazonClient amazonClient;

    public ExistFileUsecase(FilePort filePort, AmazonClient amazonClient) {
        this.filePort = filePort;
        this.amazonClient = amazonClient;
    }

    public boolean excute(String fileName){
       try {
           Optional<File> file = filePort.downloadFile(fileName);
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
