package com.m.blog.controller;

import com.google.gson.JsonObject;
import com.m.blog.repository.FileJpaRepository;
import com.m.blog.service.FileDownloadService;
import com.m.blog.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RequestMapping("/file")
@Controller
public class FileController {
    @Autowired
    FileJpaRepository fileJpaRepository;

    @Autowired
    FileDownloadService fileDownloadService;
    @Autowired
    FileUploadService fileUploadService;

    @PostMapping(value="/upload", produces = "application/json")
    @ResponseBody
    public JsonObject uploadSummernoteImageFile(@RequestParam("file") MultipartFile multipartFile) throws IOException {

        JsonObject jsonObject = new JsonObject();

        String originalFileName = multipartFile.getOriginalFilename();	//오리지날 파일명
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));	//파일 확장자
        String savedFileName = UUID.randomUUID() + extension;	//저장될 파일 명
        String dirName="static";
        try {
            fileUploadService.upload(multipartFile, dirName, savedFileName);
            jsonObject.addProperty("originalFileName", originalFileName);
            jsonObject.addProperty("fileName", savedFileName);
            jsonObject.addProperty("url", "/file/download/"+savedFileName);
            jsonObject.addProperty("downloadUrl", "/file/download/"+savedFileName);
            jsonObject.addProperty("responseCode", "success");
            com.m.blog.entity.File file= new com.m.blog.entity.File(savedFileName, originalFileName, dirName);
            fileJpaRepository.save(file);
        } catch (IOException e) {
            jsonObject.addProperty("responseCode", "error");
            e.printStackTrace();
        }

        return jsonObject;
    }

    /*
    @PostMapping(value="/uploadSummernoteImageFile", produces = "application/json")
    @ResponseBody
    public JsonObject uploadSummernoteImageFile(@RequestParam("file") MultipartFile multipartFile) {

        JsonObject jsonObject = new JsonObject();

        String fileRoot = "C:\\summernote_image\\";	//저장될 외부 파일 경로
        String originalFileName = multipartFile.getOriginalFilename();	//오리지날 파일명
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));	//파일 확장자
        String savedFileName = UUID.randomUUID() + extension;	//저장될 파일 명
        File targetFile = new File(fileRoot + savedFileName);

        home.portfolio.artifact.entity.File file= new home.portfolio.artifact.entity.File(savedFileName, originalFileName, fileRoot);
        fileJpaRepository.save(file);
        try {
            InputStream fileStream = multipartFile.getInputStream();
            FileUtils.copyInputStreamToFile(fileStream, targetFile);	//파일 저장
            jsonObject.addProperty("originalFileName", originalFileName);
            jsonObject.addProperty("fileName", savedFileName);
            jsonObject.addProperty("url", "/download/"+savedFileName);
            jsonObject.addProperty("downloadUrl", "/download/"+savedFileName);
            jsonObject.addProperty("responseCode", "success");

        } catch (IOException e) {
            FileUtils.deleteQuietly(targetFile);	//저장된 파일 삭제
            jsonObject.addProperty("responseCode", "error");
            e.printStackTrace();
        }

        return jsonObject;
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<Resource> fileDownload(@PathVariable("fileName") String fileName) throws IOException {
        home.portfolio.artifact.entity.File file = fileJpaRepository.getById(fileName);
        Path path = Paths.get(file.getFilePath()+"\\"+file.getFileName());
        Resource resource =  new InputStreamResource(Files.newInputStream(path));
        String convertedFileName = new String(file.getOriginalFileName().getBytes("UTF-8"),"ISO-8859-1");
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + convertedFileName + "\"")
                .body(resource);
    }*/

    @GetMapping("/download/{fileName}")
    public ResponseEntity<Resource> download(@PathVariable("fileName") String fileName) throws IOException{
        return fileDownloadService.getObject(fileName);
    }
}
