package com.m.blog.aggregate.file.application.usecase;

import com.m.blog.aggregate.file.application.domain.File_;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileUploadUsecase {
    File_ upload(String postingId, MultipartFile multipartFile) throws IOException;
}
