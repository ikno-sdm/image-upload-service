package com.microservices2.cargaimagenes.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/app/UploadService")
public class CargaImagenesService {

    @PostMapping("/BatchReceiver")
    public ResponseEntity<Object> batchReceiver(@RequestParam("file") MultipartFile file,
            @RequestParam("projectID") String project) {
        try {
            System.out.println("SERVICIO ONLINE");
            return new ResponseEntity<Object>("SERVICIO ONLINE - CREATED", null, HttpStatus.CREATED);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error: " + e);
        }
    }

}
