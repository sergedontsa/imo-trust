package com.trust.gestion.controllers;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

@RestController
public class FileController {
    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        String filePath = System.getProperty("user.dir") + "/Uploads" + File.separator + file.getOriginalFilename();
        String fileUploadStatus;
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            fileOutputStream.write(file.getBytes());
            fileOutputStream.close();
            fileUploadStatus = "File Uploaded Successfully";
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fileUploadStatus;
    }
    @GetMapping(value = "/getFile")
    public String[] getFile() {
        String folderPath = System.getProperty("user.dir") + "/Uploads";
        File directory = new File(folderPath);
        return directory.list();
    }
    @GetMapping(value = "/download/{path:.+}")
    public ResponseEntity downloadFile(@PathVariable("path") String filename) throws FileNotFoundException {
        String fileUploadpath = System.getProperty("user.dir") + "/Uploads";
        String[] filenames = this.getFile();
        boolean contains = Arrays.asList(filenames).contains(filename);

        if (!contains) {
            return ResponseEntity.notFound().build();
        }
        String filePath = fileUploadpath + File.separator + filename;
        File file = new File(filePath);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        HttpHeaders headers = new HttpHeaders();

        String contentType = "application/octet-stream";
        String headerValue = "attachment; filename=\"" + resource.getFilename() + "\"";
        headers.add(HttpHeaders.CONTENT_DISPOSITION, headerValue);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .headers(headers)
                .body(resource);
    }
}
