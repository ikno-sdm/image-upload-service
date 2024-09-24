package com.microservices2.cargaimagenes.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.microservices2.cargaimagenes.service.FileService;

@RestController
@RequestMapping("/files")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(
        @RequestParam("file") MultipartFile file,
        @RequestParam("projectId") int projectId
        ) {
        
        boolean result = fileService.processFile(file, projectId);
        if (result) {
            return ResponseEntity.ok("File uploaded successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File uploaded failed");
        }
    }
}
