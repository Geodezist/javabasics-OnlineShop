package ua.com.bpgdev.onlineshop.web.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IdParser {
    private final static Pattern PATTERN = Pattern.compile("^\\/([0-9]+)$");

    public static int getId(String value) {
        String stringID = "";
        Matcher matcher = PATTERN.matcher(value);
        while (matcher.find()) {
            stringID = matcher.group(1);
        }
        return Integer.parseInt(stringID);
    }
}
