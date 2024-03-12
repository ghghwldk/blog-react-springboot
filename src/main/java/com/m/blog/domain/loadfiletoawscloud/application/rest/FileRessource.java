package com.m.blog.domain.loadfiletoawscloud.application.rest;

import com.m.blog.domain.loadfiletoawscloud.application.dto.FileDto;
import com.m.blog.domain.loadfiletoawscloud.domain.mapper.FileMapper;
import com.m.blog.domain.loadfiletoawscloud.domain.model.File;
import com.m.blog.domain.loadfiletoawscloud.domain.use_case.DownloadFile;
import com.m.blog.domain.loadfiletoawscloud.domain.use_case.ExistFile;
import com.m.blog.domain.loadfiletoawscloud.domain.use_case.UploadFile;
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
    private final UploadFile uploadFile;

    private final DownloadFile downloadFile;

    private final ExistFile existFile;

    public FileRessource(UploadFile uploadFile, DownloadFile downloadFile, ExistFile existFile){
        this.uploadFile = uploadFile;
        this.downloadFile = downloadFile;
        this.existFile = existFile;
    }

    @PostMapping("/uploadFile")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile multipartFile, @RequestParam("type") String typeDocument) throws IOException {
        if(multipartFile.isEmpty()){
            return new ResponseEntity<>("File cannot be empty", HttpStatus.NOT_FOUND);
        }
        if(multipartFile.getSize()>800000){
            return new ResponseEntity<>("file must have more than 8MB",HttpStatus.NOT_ACCEPTABLE);
        }

        if(existFile.excute(multipartFile.getOriginalFilename())){
            return new ResponseEntity<>("file already exist please choose another file",HttpStatus.NOT_ACCEPTABLE);
        }
        File file = FileMapper.mapFileDtoToFile(new FileDto(multipartFile.getOriginalFilename(),multipartFile.getContentType(),multipartFile.getSize(),
                typeDocument, multipartFile.getBytes()));

        return new ResponseEntity<>(uploadFile.excute(file), HttpStatus.CREATED);
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
        FileDto fileDto = FileMapper.mapFileToFileDto(downloadFile.excute(fileName));
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(fileDto.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "file = "+fileDto.getName())
                .body(new ByteArrayResource(fileDto.getData()));

    }
}
