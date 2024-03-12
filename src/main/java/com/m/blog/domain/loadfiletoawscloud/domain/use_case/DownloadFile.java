package com.m.blog.domain.loadfiletoawscloud.domain.use_case;

import com.m.blog.domain.loadfiletoawscloud.aws.AmazonClient;
import com.m.blog.domain.loadfiletoawscloud.domain.model.File;
import com.m.blog.domain.loadfiletoawscloud.domain.port.PortFile;
import com.m.blog.domain.loadfiletoawscloud.util.FileUtils;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@Component
public class DownloadFile {

    private final PortFile portFile;
    private final AmazonClient amazonClient;

    public DownloadFile(PortFile portFile, AmazonClient amazonClient) {
        this.portFile = portFile;
        this.amazonClient = amazonClient;
    }

    public File excute(String fileName) throws IOException {
        File file = portFile.downloadFile(fileName).get();
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
