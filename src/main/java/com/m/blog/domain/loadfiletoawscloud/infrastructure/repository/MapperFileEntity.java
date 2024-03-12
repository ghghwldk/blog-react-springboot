package com.m.blog.domain.loadfiletoawscloud.infrastructure.repository;

import com.m.blog.domain.loadfiletoawscloud.application.domain.File;

import java.util.Optional;

public class MapperFileEntity {

    private MapperFileEntity(){
        throw new IllegalStateException("MapperFileEntity class");
    }

    public static FileEntity2 mapFileToFileEntity(File file){
        if(file ==null){
            return null;
        }
        FileEntity2 fileEntity2 = new FileEntity2();
        fileEntity2.setName(file.getName());
        fileEntity2.setContentType(file.getContentType());
        fileEntity2.setSize(file.getSize());
        fileEntity2.setFileNature(file.getFileNature());
        fileEntity2.setData(file.getData());
        fileEntity2.setUuidAwsFile(file.getUuidAwsFile());
        return fileEntity2;
    }

    public static Optional<File> mapFileEntityToFile(FileEntity2 fileEntity2) {
        Optional<File> file = Optional.of(new File(fileEntity2.getName(),
                fileEntity2.getContentType(),
                fileEntity2.getSize(),
                fileEntity2.getFileNature(),
                fileEntity2.getData(),
                fileEntity2.getUuidAwsFile()));
        return Optional.of(file.orElse(null));
    }
}
