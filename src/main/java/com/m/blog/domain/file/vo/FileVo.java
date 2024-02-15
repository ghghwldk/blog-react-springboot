package com.m.blog.domain.file.vo;

import com.m.blog.domain.file.dto.FileUploadResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class FileVo {
    private String originalFileName;
    private String extension;
    private String savedFileName;
    private MultipartFile multipartFile;

    public static FileVo of(MultipartFile multipartFile){
        String originalFileName = multipartFile.getOriginalFilename();
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));

        return FileVo.builder()
                .originalFileName(originalFileName)
                .extension(extension)
                .savedFileName(UUID.randomUUID() + extension)
                .build();
    }
}

