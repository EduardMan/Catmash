package ru.mansurov.catmash.util;

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
}
