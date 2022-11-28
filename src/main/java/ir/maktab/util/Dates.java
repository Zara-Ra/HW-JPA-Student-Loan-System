package ir.maktab.util;

import ir.maktab.data.enums.DegreeType;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class Dates {
    private static final int A_WEEK = 7;
    private static final LocalDateTime FIRST_RANGE_START = LocalDateTime.of(2022, 10, 23, 0, 0);
    private static final LocalDateTime FIRST_RANGE_END = LocalDateTime.of(2022, 10, 29, 23, 59);

    private static final LocalDateTime SECOND_RANGE_START = LocalDateTime.of(2023, 2, 14, 0, 0);
    private static final LocalDateTime SECOND_RANGE_END = LocalDateTime.of(2023, 2, 20, 23, 59);

    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        ZonedDateTime zdt = localDateTime.atZone(ZoneId.systemDefault());
        return Date.from(zdt.toInstant());
    }

    public static LocalDateTime dateToLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    public static boolean isInRange(Date date) {
        LocalDateTime testDate = dateToLocalDateTime(date);
        TriFunction inRange = (t, s, e) -> t.equals(s) || t.equals(e) || (t.isAfter(s) && t.isBefore(e));
        return inRange.test(testDate, FIRST_RANGE_START, FIRST_RANGE_END)
                || inRange.test(testDate, SECOND_RANGE_START, SECOND_RANGE_END);
    }

    public static long numberOfDaysBetween(Date firstDate, Date secondDate) {
        LocalDateTime first = dateToLocalDateTime(firstDate);
        LocalDateTime second = dateToLocalDateTime(secondDate);
        return Duration.between(first, second).toDays();
    }

    public static boolean areDatesInSameSemester(Date firstDate, Date secondDate) {
        long daysBetween = Math.abs(numberOfDaysBetween(firstDate, secondDate));
        return daysBetween < A_WEEK;
    }

    public static boolean areDatesInSameGrade(Date firstDate, Date secondDate, DegreeType degreeType){
        long daysBetween = Math.abs(numberOfDaysBetween(firstDate, secondDate));
        int graduate = degreeType.graduateYears;
        long graduationInDays = graduate * 365;
        return graduationInDays > daysBetween;
    }
}
