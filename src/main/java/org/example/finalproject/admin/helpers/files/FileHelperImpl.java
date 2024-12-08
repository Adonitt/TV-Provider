package org.example.finalproject.admin.helpers.files;

import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.concurrent.RecursiveTask;

@Component
public class FileHelperImpl implements FileHelper {
    @Override
    public String uploadFile(String folder, String fileName, byte[] fileContent) {
        try {
            var path = Paths.get(folder); // kur ta boj upload file, ma jep folderin

            if (!Files.exists(path)) { // shiko se a ekziston , nese jo krijoje te ri si folder
                Files.createDirectories(path);
            }

            String newFileName = UUID.randomUUID() + "_" + fileName; // e shenojme file se qysh ka me u rujte
            Path pathToFile = Paths.get(path.toString(), newFileName);

            Files.write(pathToFile, fileContent);

            return newFileName;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;

        }
    }
}
