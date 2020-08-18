package utils;

import java.io.File;
import java.net.URL;

public class Settings {
    public static String original_img_url;
    public static String img_path;
    public static final int essay_number_for_eachPage = 10;

    static {
        URL resource = Settings.class.getClassLoader().getResource("img/img.jpeg");
        original_img_url = resource.getPath();
        File file = new File(original_img_url);
        img_path = file.getParent();
    }
}
