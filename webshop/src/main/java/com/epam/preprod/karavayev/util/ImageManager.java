package com.epam.preprod.karavayev.util;

import java.io.IOException;
import javax.servlet.http.Part;

public class ImageManager {

    public static void saveImage(String path, Part image) throws IOException {
        image.write(path);
    }
}