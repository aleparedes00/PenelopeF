package com.penelopef.tools;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTools {

    static String dateFormat = "dd/MM/yyyy - HH:mm:ss";
    static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(dateFormat);

    public static String now() {
        LocalDateTime nowDateTime = LocalDateTime.now();
        return nowDateTime.format(dateFormatter);
    }
}