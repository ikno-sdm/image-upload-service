package com.microservices2.cargaimagenes.service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "project")
public class Project {

    @Id
    private int id;

    private String processingDirectory;

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProcessingDirectory() {
        return processingDirectory;
    }

    public void setProcessingDirectory(String processingDirectory) {
        this.processingDirectory = processingDirectory;
    }
}
