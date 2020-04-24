package ru.mansurov.catmash.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;
import ru.mansurov.catmash.model.Target;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Utils {

    public static boolean checkFolderExistence(String picturesPath) {
        boolean success = true;
        File fileMy = new File(picturesPath);
        if (!fileMy.exists()) {
            success = fileMy.mkdir();
        }
        return success;
    }

    public static String getFileNameWithoutExtension(String fileName) {
        return fileName.replaceFirst("[.][^.]+$", "");
    }

    public static List<Long> getIdsFromCookieString(String cookieString) {
        return Arrays.stream(cookieString.split("\\."))
                .filter(s -> !s.isEmpty())
                .map(Long::valueOf)
                .collect(Collectors.toList());
    }

    public static void DeleteTargetFiles(List<Target> targets, String picturesPath) {
        for (Target target :
             targets) {
            File targetFile = new File(picturesPath + "/" + target.getFileName());
            targetFile.delete();
        }
    }

    public static boolean checkSizeOfFiles(MultipartFile[] files, int pictureMaxSize) {
        return Arrays.stream(files)
                .allMatch(file -> file.getSize() / 1024 <= pictureMaxSize);
    }
}
