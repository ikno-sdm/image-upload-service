package com.microservices2.cargaimagenes.persistence;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.stereotype.Component;

import com.microservices2.cargaimagenes.domain.port.FileRepositoryPort;
import com.microservices2.cargaimagenes.service.dto.FileDTO;

@Component
public class FileSystemRepository implements FileRepositoryPort {

    Random random = new Random();
    
    private static final Logger logger = Logger.getLogger(FileSystemRepository.class.getName());
    
    @Override
    public String save(FileDTO fileDTO, int projectId) throws IOException {

        String generatedId = generateUniqueId();
        String fileName = fileDTO.getName();

        String baseName = fileName.substring(0, fileName.lastIndexOf('.'));
        String extension = fileName.substring(fileName.lastIndexOf('.'));
        String newFileName = baseName + "-" + generatedId + extension;

        // Usar el workflowDirectory pasado desde el servicio
        String storageDirectory = fileDTO.getWorkflowDirectory() + File.separator + projectId + "_backup";
        File storageDir = new File(storageDirectory);

        if (!storageDir.exists() && !storageDir.mkdirs()) {
            logger.log(Level.SEVERE, "Error al crear el directorio backup: {0}", storageDirectory);
            throw new IOException("Error: No se pudo crear el directorio de almacenamiento en " + storageDirectory);
        }

        File domainFile = new File(storageDirectory, newFileName);

        try {
            Path filePath = domainFile.toPath();
            Files.write(filePath, fileDTO.getContent());
        
            logger.log(Level.INFO, "El archivo fue guardado exitosamente: {0}", domainFile.getAbsolutePath());
            return String.valueOf(domainFile.getAbsolutePath());
        
        } catch (IOException e) {
            logger.log(Level.SEVERE, () -> "Error al crear el directorio backup: " + storageDirectory);
            throw new IOException("Error al guardar el archivo en " + domainFile.getAbsolutePath(), e);
        }
    }

    // Generar un ID aleatorio de 13 dígitos
    private String generateUniqueId() {
        long timestamp = Instant.now().toEpochMilli();  // Marca de tiempo en milisegundos
        int randomSuffix = random.nextInt(1000);  // Genera un número aleatorio de 3 dígitos
        // Combina el timestamp (10 dígitos) con el número aleatorio (3 dígitos) para obtener un ID de hasta 13 caracteres
        return String.format("%010d%03d", timestamp % 1_000_000_0000L, randomSuffix);
    }
}
