package com.microservices2.cargaimagenes.persistence;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Component;

import com.microservices2.cargaimagenes.domain.port.FileProcessingPort;

@Component
public class FileConvertRepository implements FileProcessingPort{
    
    @Override
    public boolean process(File file) {
        // try {
            return true;
        // } catch (IOException e) {
        //     e.printStackTrace();
        //     return false;
        // }
    }
}
