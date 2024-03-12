package com.m.blog.domain.loadfiletoawscloud.adapter.entrypoint.api;


import com.m.blog.domain.loadfiletoawscloud.application.domain.File;
import com.m.blog.domain.loadfiletoawscloud.application.port.persistence.FilePort;
import com.m.blog.domain.loadfiletoawscloud.infrastructure.repository.MapperFileEntity;
import com.m.blog.domain.loadfiletoawscloud.infrastructure.repository.RepositoryPersistence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FileAdapter implements FilePort {

    private final RepositoryPersistence repositoryDatabase;


    @Override
    public String uploadFile(File file) {
        //stocker le fichier
        repositoryDatabase.saveFile(MapperFileEntity.mapFileToFileEntity(file));
        return "fichier enregistrer avec seccés";
    }

    @Override
    public Optional<File> downloadFile(String fileName) throws FileNotFoundException {
        return MapperFileEntity.mapFileEntityToFile(repositoryDatabase.get(fileName));
    }
}
