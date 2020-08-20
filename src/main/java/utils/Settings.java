package utils;

import java.io.File;
import java.net.URL;

public class Settings {
    public static String original_img_url = "/img.jpeg";
    public static String img_path;
    public static final int essay_number_for_eachPage = 10;

    static {
        URL resource = Settings.class.getClassLoader().getResource("/img/img.jpeg");
        String path = resource.getPath();
        File file = new File(path);
        img_path = file.getParent();
    }
}
