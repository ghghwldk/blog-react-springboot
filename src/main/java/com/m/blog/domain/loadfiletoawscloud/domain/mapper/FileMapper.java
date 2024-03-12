package com.m.blog.domain.loadfiletoawscloud.domain.mapper;


import com.m.blog.domain.loadfiletoawscloud.application.dto.FileDto;
import com.m.blog.domain.loadfiletoawscloud.domain.model.File;
import com.m.blog.domain.loadfiletoawscloud.util.FileNature;
import com.m.blog.domain.loadfiletoawscloud.util.FileUtils;

public class FileMapper {

    private FileMapper(){
        throw new IllegalStateException("FileMapper class");
    }

    public static File mapFileDtoToFile(FileDto fileDto){
        if(fileDto == null){
            return null;
        }
        return new File(fileDto.getName(), fileDto.getContentType(), fileDto.getSize(),
                FileNature.valueOf(fileDto.getFileNature()), fileDto.getData(), FileUtils.generateFileName(fileDto.getName()));
    }

    public static FileDto mapFileToFileDto(File file) {
        if(file == null){
            return null;
        }
        return new FileDto(file.getName(),
                file.getContentType(),
                file.getSize(),
                String.valueOf(file.getFileNature()),
                file.getData());
    }
}