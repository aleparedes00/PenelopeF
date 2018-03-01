package com.penelopef.tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTools {
    public static boolean match(String toCheck, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(toCheck);

        return matcher.matches();
    }
}
