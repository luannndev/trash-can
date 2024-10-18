package dev.luan.trashcan.cleaner;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FolderCleaner {

    public void clearFolder(String folderPath) {
        try {
            Files.walk(Paths.get(folderPath))
                    .map(Path::toFile)
                    .sorted((o1, o2) -> -o1.compareTo(o2))
                    .forEach(File::delete);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}