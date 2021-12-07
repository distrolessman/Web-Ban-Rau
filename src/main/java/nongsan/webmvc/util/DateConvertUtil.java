package nongsan.webmvc.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateConvertUtil {
    public static Date convert(String date) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            return formatter.parse(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
