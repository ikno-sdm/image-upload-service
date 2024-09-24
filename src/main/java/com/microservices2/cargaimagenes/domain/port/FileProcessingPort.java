package com.microservices2.cargaimagenes.domain.port;

import java.io.File;

public interface FileProcessingPort {
    boolean process(File file);
}
