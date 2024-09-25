package com.microservices2.cargaimagenes.web;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.microservices2.cargaimagenes.service.FileService;
import com.microservices2.cargaimagenes.service.dto.FileDTO;

@RestController
@RequestMapping("/files")
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(
        @RequestParam("file") MultipartFile file,
        @RequestParam("projectId") int projectId
        ) {
        try {

            FileDTO fileDTO = new FileDTO();
            fileDTO.setName(file.getOriginalFilename());
            fileDTO.setContent(file.getBytes());

            String filePath = fileService.processFile(fileDTO, projectId);
        
            return ResponseEntity.ok(filePath);

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(e.getMessage());
        }
    }
}
