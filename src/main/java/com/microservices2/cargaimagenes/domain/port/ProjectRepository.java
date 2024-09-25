package com.microservices2.cargaimagenes.domain.port;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.microservices2.cargaimagenes.service.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
    
    @Query("SELECT p.processingDirectory FROM Project p WHERE p.id = :projectId")
    String findByProjectIdProcessingDirectory(int projectId);
}