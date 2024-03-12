package com.m.blog.domain.loadfiletoawscloud.infra.repository;

import com.m.blog.domain.loadfiletoawscloud.infra.entity.FileEntity;
import com.m.blog.domain.loadfiletoawscloud.infra.port.FileOperation;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;

@Component
public class RepositoryDatabase implements FileOperation {

    private final RepositoryFile repositoryFile;

    public RepositoryDatabase(RepositoryFile repositoryFile) {
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
