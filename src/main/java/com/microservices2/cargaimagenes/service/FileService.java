package com.microservices2.cargaimagenes.service;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.microservices2.cargaimagenes.domain.port.FileRepositoryPort;
import com.microservices2.cargaimagenes.domain.port.ProjectRepository;
import com.microservices2.cargaimagenes.service.dto.FileDTO;

@Service
public class FileService {

    private final FileRepositoryPort fileRepository;
    private final ProjectRepository projectRepository;

    public FileService(FileRepositoryPort fileRepository, ProjectRepository projectRepository) {
        this.fileRepository = fileRepository;
        this.projectRepository = projectRepository;
    }

    public String processFile(FileDTO fileDTO, int projectId) throws IOException {
        String fileName = fileDTO.getName();

        if (isValidFileFormat(fileName)) {
        
            fileDTO.setWorkflowDirectory(projectRepository.findByProjectIdProcessingDirectory(projectId));

            
            if (fileDTO.getWorkflowDirectory() == null || fileDTO.getWorkflowDirectory().isEmpty()) {
                throw new IOException("No se encontró el directorio de procesamiento para el proyecto con ID: " + projectId);
            }

            return fileRepository.save(fileDTO, projectId);

        } else {
            throw new IOException("Formato de archivo no válido");
        }
    }

    // Método auxiliar para validar el formato de archivo
    private boolean isValidFileFormat(String fileName) {
        String regex = ".*\\.(tiff|tif|pdf|jpg|jpeg|rar|zip|7z|tar|gz|bz2|xz)$";
        return fileName.toLowerCase().matches(regex);
    }
}
