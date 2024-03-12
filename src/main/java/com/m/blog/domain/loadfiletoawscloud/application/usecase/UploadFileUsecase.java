package com.m.blog.domain.loadfiletoawscloud.application.usecase;

import com.m.blog.domain.loadfiletoawscloud.application.domain.File;
import com.m.blog.domain.loadfiletoawscloud.application.port.file.FilePort;
import com.m.blog.domain.loadfiletoawscloud.application.port.persistence.FilePersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UploadFileUsecase {

    private final FilePersistencePort filePersistencePort;
    private final FilePort filePort;

    public String excute(File file){
        //vérifier l'existance du fichier
        // vérifier la taille du fichier
        //autre traitement.
        filePort.uploadFile(file);
        return filePersistencePort.uploadFile(file);
    }
}
