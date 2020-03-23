package ru.mansurov.catmash;

import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;

public class UtilsForTests {

    public static String generateMessageForMash(boolean fitMessage, int minMashMessage) {
        StringBuilder sb = new StringBuilder("testMessage");
        while (sb.length() < minMashMessage) {
            sb.append("testMessage");
        }
        if (fitMessage) {
            return sb.substring(0, minMashMessage);
        }
        return sb.substring(0, minMashMessage - 1);
    }

    public static void addTargetsForMash(boolean fitCount, int minTargetsCount, String mashName,
                                         MockMultipartHttpServletRequestBuilder multipartBuilder) {
        for (int i = 0; i < minTargetsCount - 2; i++) {
            multipartBuilder.file("files", ("testTarget_" + mashName + i).getBytes());
        }
        if (fitCount) {
            multipartBuilder.file("files", ("testTarget_" + mashName + (minTargetsCount - 1)).getBytes());
            multipartBuilder.file("files", ("testTarget_" + mashName + minTargetsCount).getBytes());
        }
    }

    public static String generateNameForMash(boolean fitMessage, int minMashNameLength) {
        StringBuilder sb = new StringBuilder("testName");
        while (sb.length() < minMashNameLength) {
            sb.append("testMessage");
        }
        if (fitMessage) {
            return sb.substring(0, minMashNameLength);
        }
        return sb.substring(0, minMashNameLength - 1);
    }

}
