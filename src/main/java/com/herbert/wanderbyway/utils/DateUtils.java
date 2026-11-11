package com.herbert.wanderbyway.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class DateUtils  {

    public static String getTodayString(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateFormat.TEQUILA.getValue());
        LocalDate today = LocalDate.now();
        return today.format(formatter);
    }

    public static String weeksFromNowString(int weeks){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateFormat.TEQUILA.getValue());
        LocalDate weekFromNow = LocalDate.now().plusWeeks(weeks);
        return weekFromNow.format(formatter);
    }

    public static String formatDateToString(LocalDateTime date, DateFormat format){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format.getValue());
        return date.format(formatter);
    }

    public static String formatDateToString(ZonedDateTime date, DateFormat format){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format.getValue());
        return date.format(formatter);
    }

    public static String formatDateToString(Date date, DateFormat format){
        SimpleDateFormat formatter = new SimpleDateFormat(format.getValue());
        return formatter.format(date);
    }

    public static LocalDateTime atMidnight(Date date) {
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate()
                .atStartOfDay();
    }

    public static List<LocalDateTime> getDatesBetween(LocalDateTime startDate, LocalDateTime endDate) {
        List<LocalDateTime> dates = new ArrayList<>();
        while (!startDate.isAfter(endDate)) {
            dates.add(startDate);
            startDate = startDate.plusDays(1);
        }
        return dates;
    }

    public static Date parseDate(String dateString, DateFormat format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format.getValue());
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date '" + dateString + "', expected format " + format.getValue(), e);
        }
    }
}