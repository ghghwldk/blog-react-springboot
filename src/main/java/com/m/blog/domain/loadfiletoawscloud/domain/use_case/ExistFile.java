package com.m.blog.domain.loadfiletoawscloud.domain.use_case;

import com.m.blog.domain.loadfiletoawscloud.aws.AmazonClient;
import com.m.blog.domain.loadfiletoawscloud.domain.model.File;
import com.m.blog.domain.loadfiletoawscloud.domain.port.PortFile;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.util.Optional;

@Component
public class ExistFile {

    private final PortFile portFile;

    private final AmazonClient amazonClient;

    public ExistFile(PortFile portFile, AmazonClient amazonClient) {
        this.portFile = portFile;
        this.amazonClient = amazonClient;
    }

    public boolean excute(String fileName){
       try {
           Optional<File> file = portFile.downloadFile(fileName);
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
