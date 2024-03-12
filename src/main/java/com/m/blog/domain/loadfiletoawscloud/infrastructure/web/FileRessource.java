package com.m.blog.domain.loadfiletoawscloud.infrastructure.web;

import com.m.blog.domain.loadfiletoawscloud.application.domain.FileMapper;
import com.m.blog.domain.loadfiletoawscloud.application.domain.File;
import com.m.blog.domain.loadfiletoawscloud.application.usecase.DownloadFileUsecase;
import com.m.blog.domain.loadfiletoawscloud.application.usecase.ExistFileUsecase;
import com.m.blog.domain.loadfiletoawscloud.application.usecase.UploadFileUsecase;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@RestController()
public class FileRessource {
    private final UploadFileUsecase uploadFileUsecase;

    private final DownloadFileUsecase downloadFileUsecase;

    private final ExistFileUsecase existFileUsecase;

    public FileRessource(UploadFileUsecase uploadFileUsecase, DownloadFileUsecase downloadFileUsecase, ExistFileUsecase existFileUsecase){
        this.uploadFileUsecase = uploadFileUsecase;
        this.downloadFileUsecase = downloadFileUsecase;
        this.existFileUsecase = existFileUsecase;
    }

    @PostMapping("/uploadFile")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile multipartFile, @RequestParam("type") String typeDocument) throws IOException {
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

    @PostMapping("/uploadMultipleFiles")
    public List<ResponseEntity<String>> uploadMutipleFile(@RequestParam("file") MultipartFile[] files, @RequestParam("type") String type){
        return Collections.emptyList();
//        return  Arrays.stream(files)
//                .map(file -> upload(file, type))
//                .toList();
    }

    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String fileName) throws IOException {
        FileDto fileDto = FileMapper.mapFileToFileDto(downloadFileUsecase.excute(fileName));
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(fileDto.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "file = "+fileDto.getName())
                .body(new ByteArrayResource(fileDto.getData()));

    }
}
