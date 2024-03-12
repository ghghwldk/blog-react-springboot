package com.m.blog.domain.loadfiletoawscloud.domain.use_case;

import com.m.blog.domain.loadfiletoawscloud.aws.AmazonClient;
import com.m.blog.domain.loadfiletoawscloud.domain.model.File;
import com.m.blog.domain.loadfiletoawscloud.domain.port.PortFile;
import com.m.blog.domain.loadfiletoawscloud.util.FileUtils;
import org.springframework.stereotype.Component;

@Component
public class UploadFile {

    private final PortFile portFile;

    private final AmazonClient amazonClient;

    public UploadFile(PortFile portFile, AmazonClient amazonClient) {
        this.portFile = portFile;
        this.amazonClient = amazonClient;
    }

    public String excute(File file){
        //vérifier l'existance du fichier
        // vérifier la taille du fichier
        //autre traitement.
        amazonClient.uploadFileToBucket(file.getUuidAwsFile(), FileUtils.bytesToFile(file.getData(), file.getName()), String.valueOf(file.getFileNature()));
        return portFile.uploadFile(file);
    }
}
