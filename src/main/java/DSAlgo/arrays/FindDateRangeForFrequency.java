package DSAlgo.arrays;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;


public class FindDateRangeForFrequency {

    public enum Frequency {
        DAILY, WEEKLY, FORTNIGHTLY, MONTHLY, QUARTERLY, YEARLY, CUSTOM
    }

    public static void printValidityDateRange(LocalDate givenDate, Frequency frequency){
        String startDate = null;
        String endDate = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        switch (frequency){

            case DAILY:
                startDate = givenDate.format(formatter);
                endDate = givenDate.format(formatter);
                break;
            case WEEKLY:
                LocalDate firstDayOfWeek = givenDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
                LocalDate lastDayOfWeek = givenDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
                startDate = firstDayOfWeek.format(formatter);
                endDate = lastDayOfWeek.format(formatter);
                break;
            case FORTNIGHTLY:
                LocalDate firstDayOfMonth = givenDate.with(TemporalAdjusters.firstDayOfMonth());
                LocalDate endOfFirstFortnight = firstDayOfMonth.plusDays(14);

                LocalDate firstDayOfFortnight;
                LocalDate lastDayOfFortnight;

                if (!givenDate.isAfter(endOfFirstFortnight)) {
                    firstDayOfFortnight = firstDayOfMonth;
                    lastDayOfFortnight = endOfFirstFortnight;
                } else {
                    firstDayOfFortnight = firstDayOfMonth.plusDays(15);
                    lastDayOfFortnight = givenDate.with(TemporalAdjusters.lastDayOfMonth());
                }
                startDate = firstDayOfFortnight.format(formatter);
                endDate = lastDayOfFortnight.format(formatter);
                break;
            case MONTHLY:
                firstDayOfMonth = givenDate.with(TemporalAdjusters.firstDayOfMonth());
                LocalDate lastDayOfMonth = givenDate.with(TemporalAdjusters.lastDayOfMonth());
                startDate = firstDayOfMonth.format(formatter);
                endDate = lastDayOfMonth.format(formatter);
                break;
            case QUARTERLY:
                int month = givenDate.getMonthValue();
                LocalDate firstDayOfQuarter;
                LocalDate lastDayOfQuarter;

                if (month >= 1 && month <= 3) {
                    firstDayOfQuarter = LocalDate.of(givenDate.getYear(), Month.JANUARY, 1);
                    lastDayOfQuarter = LocalDate.of(givenDate.getYear(), Month.MARCH, 31);
                } else if (month >= 4 && month <= 6) {
                    firstDayOfQuarter = LocalDate.of(givenDate.getYear(), Month.APRIL, 1);
                    lastDayOfQuarter = LocalDate.of(givenDate.getYear(), Month.JUNE, 30);
                } else if (month >= 7 && month <= 9) {
                    firstDayOfQuarter = LocalDate.of(givenDate.getYear(), Month.JULY, 1);
                    lastDayOfQuarter = LocalDate.of(givenDate.getYear(), Month.SEPTEMBER, 30);
                } else {
                    firstDayOfQuarter = LocalDate.of(givenDate.getYear(), Month.OCTOBER, 1);
                    lastDayOfQuarter = LocalDate.of(givenDate.getYear(), Month.DECEMBER, 31);
                }
                startDate = firstDayOfQuarter.format(formatter);
                endDate = lastDayOfQuarter.format(formatter);
                break;
            case YEARLY:
                LocalDate firstDayOfYear = givenDate.with(TemporalAdjusters.firstDayOfYear());
                LocalDate lastDayOfYear = givenDate.with(TemporalAdjusters.lastDayOfYear());
                startDate = firstDayOfYear.format(formatter);
                endDate = lastDayOfYear.format(formatter);
                break;

            case CUSTOM:

                break;

        }
        System.out.println("Frequency : " + frequency);
        System.out.println("Current Date : " + givenDate);
        System.out.println("Start Date : " + startDate);
        System.out.println("End Date : " + endDate);
        System.out.println();
    }


    public static void main(String[] args) {

        LocalDate givenDate = LocalDate.of(2024, 8, 17);

        printValidityDateRange(givenDate, Frequency.DAILY);
        printValidityDateRange(givenDate, Frequency.WEEKLY);
        printValidityDateRange(givenDate, Frequency.FORTNIGHTLY);
        printValidityDateRange(givenDate, Frequency.MONTHLY);
        printValidityDateRange(givenDate, Frequency.QUARTERLY);
        printValidityDateRange(givenDate, Frequency.YEARLY);

        String customStartDate = "2023-08-01";
        String customEndDate = "2023-09-06";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date customStartDateDate = formatter.parse(customStartDate);
            Date customEndDateDate = formatter.parse(customEndDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
