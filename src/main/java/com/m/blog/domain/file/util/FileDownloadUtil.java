package com.m.blog.domain.file.util;

import com.m.blog.domain.file.vo.DownloadFileVo;
import org.springframework.core.io.Resource;

import java.io.IOException;

public interface FileDownloadUtil {
    Resource getS3Resource(DownloadFileVo fileVo);

    Resource getLocalResource(DownloadFileVo fileVo) throws IOException;
}
