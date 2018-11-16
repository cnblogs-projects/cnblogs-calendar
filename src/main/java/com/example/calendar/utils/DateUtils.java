package com.example.calendar.utils;

import com.example.calendar.common.CalendarDate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

/**
 * @author RWM
 * @date 2018/11/16
 */
public class DateUtils {


    public static <T> List<CalendarDate<T>> calendar(int year, int month, Function<String, Optional<T>> function) {
        List<CalendarDate<T>> cdList = new ArrayList<>();
        int monthDays = monthDays(year, month);
        CalendarDate<T> cdR;
        for (int day = 1; day <= monthDays; day++) {
            cdR = new CalendarDate<>();
            cdR.day = day;
            LocalDate date = LocalDate.of(year, month, day);
            cdR.weekDay = dayOfWeek(date);
            cdR.isToday = isToday(date);
            if (function != null) {
                Optional<T> optional = function.apply(date.toString());
                if (optional.isPresent()) {
                    cdR.info = optional.get();
                }
            }
            cdList.add(cdR);
        }
        return cdList;
    }

    private static boolean isToday(LocalDate date) {
        LocalDate today = LocalDate.now();
        return date.getYear() == today.getYear() &&
                date.getMonth() == today.getMonth() &&
                date.getDayOfMonth() == today.getDayOfMonth();
    }

    private static int dayOfWeek(LocalDate date) {
        return null == date ? 0 : date.getDayOfWeek().getValue();
    }

    private static int monthDays(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DATE, 1);
        calendar.roll(Calendar.DATE, -1);
        return calendar.get(Calendar.DATE);
    }
}
