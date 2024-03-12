package com.m.blog.domain.loadfiletoawscloud.adapter.entrypoint.api;

import com.m.blog.common.Adapter;
import com.m.blog.domain.loadfiletoawscloud.application.domain.File;
import com.m.blog.domain.loadfiletoawscloud.application.domain.FileMapper;
import com.m.blog.domain.loadfiletoawscloud.application.port.entrypoint.api.FileEndpointPort;
import com.m.blog.domain.loadfiletoawscloud.application.usecase.DownloadFileUsecase;
import com.m.blog.domain.loadfiletoawscloud.application.usecase.ExistFileUsecase;
import com.m.blog.domain.loadfiletoawscloud.application.usecase.UploadFileUsecase;
import com.m.blog.domain.loadfiletoawscloud.infrastructure.web.FileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Adapter
@RequiredArgsConstructor
public class FileEndpointAdapter implements FileEndpointPort {
    private final UploadFileUsecase uploadFileUsecase;

    private final DownloadFileUsecase downloadFileUsecase;

    private final ExistFileUsecase existFileUsecase;

    @Override
    public ResponseEntity<String> upload(MultipartFile multipartFile, String typeDocument) throws IOException {
        if(multipartFile.isEmpty()){
            return new ResponseEntity<>("File cannot be empty", HttpStatus.NOT_FOUND);
        }
        if(multipartFile.getSize()>800000){
            return new ResponseEntity<>("file must have more than 8MB",HttpStatus.NOT_ACCEPTABLE);
        }

        if(existFileUsecase.excute(multipartFile.getOriginalFilename())){
            return new ResponseEntity<>("file already exist please choose another file",HttpStatus.NOT_ACCEPTABLE);
        }
        File file = FileMapper.mapFileDtoToFile(new FileDto(multipartFile.getOriginalFilename(),multipartFile.getContentType(),multipartFile.getSize(),
                typeDocument, multipartFile.getBytes()));

        return new ResponseEntity<>(uploadFileUsecase.excute(file), HttpStatus.CREATED);
    }

    @Override
    public List<ResponseEntity<String>> uploadMutipleFile(MultipartFile[] files, String type){
        return Collections.emptyList();
//        return  Arrays.stream(files)
//                .map(file -> upload(file, type))
//                .toList();
    }

    @Override
    public ResponseEntity<ByteArrayResource> downloadFile(String fileName) throws IOException {
        FileDto fileDto = FileMapper.mapFileToFileDto(downloadFileUsecase.excute(fileName));
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(fileDto.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "file = "+fileDto.getName())
                .body(new ByteArrayResource(fileDto.getData()));

    }
}
