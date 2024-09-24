package com.microservices2.cargaimagenes.service.dto;

public class FileDTO {
  
    private int projectId;
    private String ruta;

    public FileDTO(int projectId, String ruta) {
        this.projectId = projectId;
        this.ruta = ruta;
    }

    public int getProjectId() {
        return projectId;
    }
    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
    public String getRuta() {
        return ruta;
    }
    public void setRuta(String ruta) {
        this.ruta = ruta;
    }
}
