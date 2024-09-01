package com.project.number.consumer.services;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class CsvWriterService {

    @Value("${file.storage.path}")
    private String filePath;

    private BufferedWriter writer;

    @PostConstruct
    public void init() {
        try {

            Path fullPath = Paths.get(filePath).toAbsolutePath();
            File file = fullPath.toFile();

            if (file.getParentFile() != null && ! file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }

            if (! file.exists()) {
                file.createNewFile();
            }

            if (file.getParentFile() != null && ! file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }

            if (! file.exists()) {
                file.createNewFile();
            }

            writer = new BufferedWriter(new FileWriter(file, true));
        } catch (IOException e) {
            throw new RuntimeException("Failed to initialize CSV writer", e);
        }
    }

    public void writeRow(Integer number) {
        try {
            writer.write(number + ", ");
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException("Failed to write to CSV file", e);
        }
    }
}
