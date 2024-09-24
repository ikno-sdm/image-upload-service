package com.microservices2.cargaimagenes.persistence;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Component;

import com.microservices2.cargaimagenes.domain.port.FileSavePort;

@Component
public class FileSaveRepository implements FileSavePort{

    @Override
    public boolean saveFile(File file,int projectId) {
        try {
            // Lee el contenido del archivo en un array de bytes
            byte[] content = Files.readAllBytes(file.toPath());
            Path ruta = Paths.get("/home/juan/respaldo/IKDATA/IkdataCode/image-upload-service/img", file.getName());
            // Escribe los bytes en la ubicación de destino
            Files.write(ruta, content);
            return true;
        } catch (IOException e) {
            e.printStackTrace(); // Muestra la excepción para depuración
            return false;
        }
    }
}
