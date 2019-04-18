package com.scerp.app.example.web.controller;

import com.scerp.app.example.base.web.exception.AppException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.*;
import java.time.LocalDate;

@RestController
public class FileController {

    private final int buf = 1024 * 2;
    private final String pathPrefix = "D:/tmp/file/";

    @PostMapping("/upload")
    public String upload(MultipartFile[] files) {
        String savePath = null;
        if (files.length > 0)
            savePath = new StringBuilder(pathPrefix)
                    .append(LocalDate.now())
                    .toString();
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                try {
                    File dest = new File(savePath, this.generateName(file.getOriginalFilename()));
                    if (!dest.getParentFile().exists())
                        dest.getParentFile().mkdirs();
                    file.transferTo(dest);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return savePath;
    }

    private String generateName(String originalFilename) {
        int mark = originalFilename.indexOf('.');
        if (mark < 1) {
            throw new AppException("10001", "File name error: " + originalFilename);
        } else if (mark == originalFilename.length()) {
            throw new AppException("10002", "File type error: " + originalFilename);
        }
        return new StringBuilder(originalFilename.substring(0, mark))
                .append("_").append(System.currentTimeMillis())
                .append(".").append(originalFilename.substring(mark + 1))
                .toString();
    }

    @GetMapping("/download")
    public ResponseEntity<StreamingResponseBody> download(String date, String name) {
        return ResponseEntity.ok()
                .header("content-disposition", String.format("attachment; filename=\"%s\"", name))
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body((outputStream) -> {
                    String fileName = new StringBuilder(pathPrefix).append(date).append("/").append(name).toString();
                    try (InputStream in = new FileInputStream(fileName)) {
                        int len;
                        byte[] data = new byte[buf];
                        while ((len = in.read(data)) > 0) {
                            outputStream.write(data, 0, len);
                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }

}
