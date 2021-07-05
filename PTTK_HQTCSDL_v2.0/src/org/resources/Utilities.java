package org.resources;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Utilities {
    
    private static String DATE_FORMAT = "d'/'M'/'y";

    public static LocalDate toLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
    
    public static Date toDate(LocalDate localDate) {
        return java.sql.Date.valueOf(localDate);
    }
    
    public static boolean compareCloseTo(String subString, String containString) {
        if (subString == null) {
            return true;
        }
        if (containString == null) {
            return false;
        }
        
        return containString.toUpperCase().trim().contains(
            subString.toUpperCase().trim()
        );
    }
    
    
    public static String toString(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    }
    
    public static LocalDate toLocalDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern(DATE_FORMAT));
    } 
}
