package com.m.blog.domain.loadfiletoawscloud.infrastructure.repository;

import com.m.blog.domain.loadfiletoawscloud.application.port.FilePersistencePort;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;

@Component
public class RepositoryPersistence implements FilePersistencePort {

    private final RepositoryFile repositoryFile;

    public RepositoryPersistence(RepositoryFile repositoryFile) {
        this.repositoryFile = repositoryFile;
    }

    @Override
    public void saveFile(FileEntity fileEntity) {
        //stocker le fichier dans la base de donnée local.
        repositoryFile.save(fileEntity);
    }

    @Override
    public FileEntity get(String fileName) throws FileNotFoundException {
        return repositoryFile.findFirstByName(fileName)
                .orElseThrow(()-> new FileNotFoundException("File "+fileName+" not found"));
    }

    /*@Override
    public boolean fileExiste(String fileName) {
        return repositoryFile.existsByName(fileName);
    }*/
}
