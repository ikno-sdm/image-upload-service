package com.microservices2.cargaimagenes.service;

import java.io.IOException;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.microservices2.cargaimagenes.domain.port.FileProcessingPort;
import com.microservices2.cargaimagenes.domain.port.FileSavePort;

@Service
public class FileService {

    @Autowired
    private FileProcessingPort fileProcessingPort;

    @Autowired
    private FileSavePort fileRepositoryPort;

    public boolean processFile(MultipartFile file, int projectId) {
        
        String fileName = file.getOriginalFilename();
        if (isValidFileFormat(fileName)) {

            // Crear el archivo temporal en el directorio temporal del sistema
            File domainFile = new File(System.getProperty("java.io.tmpdir"), fileName);
            try {
                // Escribir el contenido del archivo subido en el archivo temporal
                Path tempPath = domainFile.toPath();
                Files.copy(file.getInputStream(), tempPath);
    
                // Procesar el archivo
                //boolean isProcess = fileProcessingPort.process(domainFile);
    
                // Almacenar el archivo en el sistema de archivos
                boolean isStored = fileRepositoryPort.saveFile(domainFile, projectId);
    
                if (!isStored) {
                    return false;
                }

                Files.deleteIfExists(tempPath);
                
                return isStored;
            } catch (IOException e) {
                e.printStackTrace(); // Manejar excepciones de IO
                return false;
            }
        } else {
            return false;
        }
    }

    public static boolean isValidFileFormat(String fileName) {
        // Regex para TIFF, PDF, JPG, JPEG, RAR, ZIP y otros formatos de compresión abiertos
        String regex = ".*\\.(tiff|tif|pdf|jpg|jpeg|rar|zip|7z|tar|gz|bz2|xz)$";
        return fileName.toLowerCase().matches(regex);
    }
}
