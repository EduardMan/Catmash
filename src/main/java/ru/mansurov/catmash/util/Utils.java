package ru.mansurov.catmash.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;
import ru.mansurov.catmash.model.Target;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
        List<Long> ids = new ArrayList<>();
        if (!cookieString.isEmpty()) {
            for (String str :
                    cookieString.split("\\.")) {
                ids.add(Long.valueOf(str));
            }
        }
        return ids;
    }

    public static void DeleteTargetFiles(List<Target> targets, String picturesPath) {
        for (Target target :
             targets) {
            File targetFile = new File(picturesPath + "/" + target.getFileName());
            targetFile.delete();
        }
    }

    public static boolean checkSizeOfFiles(MultipartFile[] files, int pictureMaxSize) {
        for (MultipartFile file :
                files) {
            if (file.getSize()/1024 > pictureMaxSize) {
                return false;
            }
        }
        return true;
    }
}
