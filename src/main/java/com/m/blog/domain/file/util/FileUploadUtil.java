package com.m.blog.domain.file.util;

import com.m.blog.domain.file.vo.UploadFileVo;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public interface FileUploadUtil {
    Optional<File> convert(MultipartFile file) throws IOException;

    void removeNewFile(File targetFile);

    void uploadOnLocal(UploadFileVo fileVo) throws IOException;

    void uploadOnS3(UploadFileVo fileVo) throws IOException;
}
