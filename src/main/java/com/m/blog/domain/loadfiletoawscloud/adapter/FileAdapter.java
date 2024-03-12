package com.m.blog.domain.loadfiletoawscloud.adapter;


import com.m.blog.domain.loadfiletoawscloud.application.domain.File;
import com.m.blog.domain.loadfiletoawscloud.application.port.FilePort;
import com.m.blog.domain.loadfiletoawscloud.infrastructure.repository.MapperFileEntity;
import com.m.blog.domain.loadfiletoawscloud.infrastructure.repository.RepositoryPersistence;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.util.Optional;

@Component
public class FileAdapter implements FilePort {

    private final RepositoryPersistence repositoryDatabase;

    public FileAdapter(RepositoryPersistence repositoryDatabase) {
        this.repositoryDatabase = repositoryDatabase;
    }

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

    /*@Override
    public boolean fileExiste(String fileName) {
        return repositoryDatabase.fileExiste(fileName);
    }*/
}
