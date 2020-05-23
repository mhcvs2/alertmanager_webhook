package com.mhc.test.alert_manager_hook.req;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");

    public static Date parseAlertManagerTime(String timeString) {
        if (timeString == null || timeString.length() != 35) return null;
        String standardTimeString = timeString.substring(0, 23);
        try {
            return DATE_FORMAT.parse(standardTimeString);
        } catch (ParseException e) {
            return null;
        }
    }

}
