package com.m.blog.domain.loadfiletoawscloud.adapter.persistence;

import com.m.blog.domain.loadfiletoawscloud.application.domain.File;
import com.m.blog.domain.loadfiletoawscloud.application.port.persistence.FilePersistencePort;
import com.m.blog.domain.loadfiletoawscloud.infrastructure.repository.FileEntity2;
import com.m.blog.domain.loadfiletoawscloud.infrastructure.repository.FileEntityRepository2;
import com.m.blog.domain.loadfiletoawscloud.infrastructure.repository.MapperFileEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FilePersistenceAdapter implements FilePersistencePort {

    private final FileEntityRepository2 fileEntityRepository2;

    @Override
    public FileEntity2 get(String fileName) throws FileNotFoundException {
        return fileEntityRepository2.findFirstByName(fileName)
                .orElseThrow(()-> new FileNotFoundException("File "+fileName+" not found"));
    }

    @Override
    public String uploadFile(File file) {
        //stocker le fichier
        fileEntityRepository2.save(MapperFileEntity.mapFileToFileEntity(file));
        return "fichier enregistrer avec seccés";
    }

    @Override
    public Optional<File> downloadFile(String fileName) throws FileNotFoundException {
        return MapperFileEntity.mapFileEntityToFile(get(fileName));
    }

    /*@Override
    public boolean fileExiste(String fileName) {
        return repositoryFile.existsByName(fileName);
    }*/
}
