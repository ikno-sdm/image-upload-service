package com.microservices2.cargaimagenes.domain.port;

import com.microservices2.cargaimagenes.service.dto.FileDTO;
import java.io.IOException;

public interface FileRepositoryPort {
    String save(FileDTO fileDTO, int projectId) throws IOException;
}
