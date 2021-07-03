package com.example.playerteam.util;

import java.time.LocalDate;
import java.time.Period;

public class Util {

    public static int calculateMonthsBetweenDates(LocalDate dateFrom, LocalDate dateTo) {
        if(dateTo == null){
            dateTo = LocalDate.now();
        }
        Period period = Period.between(dateFrom, dateTo);
        int result = period.getYears() * 12 + period.getMonths();
        if (period.getDays() > 0) {
            result++;
        }
        return result;
    }
}
