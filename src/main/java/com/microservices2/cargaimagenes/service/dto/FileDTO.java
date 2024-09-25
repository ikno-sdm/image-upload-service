package com.microservices2.cargaimagenes.service.dto;
public class FileDTO {

    private String name;
    private String workflowDirectory;
    private byte[] content;  // Datos binarios del archivo

    // Getters y Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWorkflowDirectory() {
        return workflowDirectory;
    }

    public void setWorkflowDirectory(String path) {
        this.workflowDirectory = path;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
