package com.microservices2.cargaimagenes.domain.port;

import java.io.File;

public interface FileSavePort {
    boolean saveFile(File file, int projectId);
}