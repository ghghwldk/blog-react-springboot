package com.m.blog.domain.loadfiletoawscloud.application.usecase;

import com.m.blog.domain.loadfiletoawscloud.application.domain.File;
import com.m.blog.domain.loadfiletoawscloud.application.port.FilePort;
import com.m.blog.domain.loadfiletoawscloud.infrastructure.aws.AmazonClient;
import com.m.blog.domain.loadfiletoawscloud.application.domain.FileUtils;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@Component
public class DownloadFileUsecase {

    private final FilePort filePort;
    private final AmazonClient amazonClient;

    public DownloadFileUsecase(FilePort filePort, AmazonClient amazonClient) {
        this.filePort = filePort;
        this.amazonClient = amazonClient;
    }

    public File excute(String fileName) throws IOException {
        File file = filePort.downloadFile(fileName).get();
        if(file == null) {
            throw new FileNotFoundException();
        }else {
            InputStream inputStream = amazonClient.getFileInputStream(file.getUuidAwsFile(),String.valueOf(file.getFileNature()));
            return new File(file.getName(),
                    file.getContentType(),
                    file.getSize(),
                    file.getFileNature(),
                    FileUtils.convertInputStreamToByte(inputStream),
                    file.getUuidAwsFile());
        }
    }
}
