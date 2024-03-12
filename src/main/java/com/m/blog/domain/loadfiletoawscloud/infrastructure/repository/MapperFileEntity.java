package com.m.blog.domain.loadfiletoawscloud.infrastructure.repository;

import com.m.blog.domain.loadfiletoawscloud.application.domain.File;

import java.util.Optional;

public class MapperFileEntity {

    private MapperFileEntity(){
        throw new IllegalStateException("MapperFileEntity class");
    }

    public static FileEntity mapFileToFileEntity(File file){
        if(file ==null){
            return null;
        }
        FileEntity fileEntity= new FileEntity();
        fileEntity.setName(file.getName());
        fileEntity.setContentType(file.getContentType());
        fileEntity.setSize(file.getSize());
        fileEntity.setFileNature(file.getFileNature());
        fileEntity.setData(file.getData());
        fileEntity.setUuidAwsFile(file.getUuidAwsFile());
        return fileEntity;
    }

    public static Optional<File> mapFileEntityToFile(FileEntity fileEntity) {
        Optional<File> file = Optional.of(new File(fileEntity.getName(),
                fileEntity.getContentType(),
                fileEntity.getSize(),
                fileEntity.getFileNature(),
                fileEntity.getData(),
                fileEntity.getUuidAwsFile()));
        return Optional.of(file.orElse(null));
    }
}
