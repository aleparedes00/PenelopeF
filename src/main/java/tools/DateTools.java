package tools;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTools {

    static String dateFormat = "dd/MM/yyyy - HH:mm:ss";
    static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(dateFormat);

    public static String now() {
        LocalDateTime nowDateTime = LocalDateTime.now();
        return nowDateTime.format(dateFormatter);
    }

    public static void main(String[] args) {
        System.out.println("Date: " + LocalDateTime.now().format(dateFormatter));
    }
}