package com.m.blog.domain.loadfiletoawscloud.application.usecase;

import com.m.blog.domain.loadfiletoawscloud.application.domain.File;
import com.m.blog.domain.loadfiletoawscloud.application.port.persistence.FilePersistencePort;
import com.m.blog.domain.loadfiletoawscloud.infrastructure.aws.AmazonClient;
import com.m.blog.domain.loadfiletoawscloud.application.domain.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@Component
@RequiredArgsConstructor
public class DownloadFileUsecase {

    private final FilePersistencePort filePersistencePort;
    private final AmazonClient amazonClient;


    public File excute(String fileName) throws IOException {
        File file = filePersistencePort.downloadFile(fileName).get();
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
