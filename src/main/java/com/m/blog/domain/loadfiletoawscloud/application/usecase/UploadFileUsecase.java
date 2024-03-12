package com.m.blog.domain.loadfiletoawscloud.application.usecase;

import com.m.blog.domain.loadfiletoawscloud.application.domain.File;
import com.m.blog.domain.loadfiletoawscloud.application.port.persistence.FilePersistencePort;
import com.m.blog.domain.loadfiletoawscloud.application.port.persistence.FilePort;
import com.m.blog.domain.loadfiletoawscloud.infrastructure.aws.AmazonClient;
import com.m.blog.domain.loadfiletoawscloud.application.domain.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UploadFileUsecase {

    private final FilePersistencePort filePort;

    private final AmazonClient amazonClient;

    public String excute(File file){
        //vérifier l'existance du fichier
        // vérifier la taille du fichier
        //autre traitement.
        amazonClient.uploadFileToBucket(file.getUuidAwsFile(), FileUtils.bytesToFile(file.getData(), file.getName()), String.valueOf(file.getFileNature()));
        return filePort.uploadFile(file);
    }
}
