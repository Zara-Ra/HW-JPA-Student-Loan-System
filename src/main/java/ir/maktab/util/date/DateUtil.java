package ir.maktab.util.date;

import ir.maktab.data.enums.Degree;
import lombok.experimental.UtilityClass;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
@UtilityClass // no need to add static keyword
public class DateUtil {
    private static final Date ASSUMED_TODAY_1 = DateUtil.localDateTimeToDate(LocalDateTime.of(2022, 10, 25, 0, 0));
    private static final Date ASSUMED_TODAY_2 = DateUtil.localDateTimeToDate(LocalDateTime.of(2023, 2, 15, 0, 0));
    public static final Date TODAY_DATE = DateUtil.localDateTimeToDate(LocalDateTime.now());

    private static final int DAYS_OF_WEEK = 7;
    private static final LocalDateTime FIRST_RANGE_START = LocalDateTime.of(2022, 10, 23, 0, 0);
    private static final LocalDateTime FIRST_RANGE_END = LocalDateTime.of(2022, 10, 29, 23, 59);

    private static final LocalDateTime SECOND_RANGE_START = LocalDateTime.of(2023, 2, 14, 0, 0);
    private static final LocalDateTime SECOND_RANGE_END = LocalDateTime.of(2023, 2, 20, 23, 59);

    public static Date getToday() {
        return ASSUMED_TODAY_1;
    }

    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        ZonedDateTime zdt = localDateTime.atZone(ZoneId.systemDefault());
        return Date.from(zdt.toInstant());
    }

    public static LocalDateTime dateToLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    public static boolean isInRegistrationRange(Date date) {
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
        return daysBetween < DAYS_OF_WEEK;
    }

    public static boolean areDatesInSameGrade(Date firstDate, Date secondDate, Degree degree) {
        long daysBetween = Math.abs(numberOfDaysBetween(firstDate, secondDate));
        long graduationInDays = degree.getGraduationInDays();
        return graduationInDays > daysBetween;
    }

    public static boolean isDateValid(Date date) {
        LocalDateTime testDate = dateToLocalDateTime(date);
        return testDate.isAfter(dateToLocalDateTime(getToday()));
    }

    public static Date addYearsToDate(Date date, int years) {
        LocalDateTime inputDate = dateToLocalDateTime(date);
        LocalDateTime plusYears = inputDate.plusYears(years);
        return localDateTimeToDate(plusYears);
    }

    public static int compare(Date date1, Date date2) {
        LocalDateTime localDateTime1 = dateToLocalDateTime(date1);
        LocalDateTime localDateTime2 = dateToLocalDateTime(date2);
        return localDateTime1.compareTo(localDateTime2);
    }

    public static Date addMonthToDate(Date date) {
        LocalDateTime inputDate = dateToLocalDateTime(date);
        LocalDateTime resultDate = inputDate.plusMonths(1);
        return localDateTimeToDate(resultDate);
    }

    public static String dateWithoutTime(Date dueDate) {
        LocalDateTime localDateTime = DateUtil.dateToLocalDateTime(dueDate);
        return localDateTime.getYear() + "/" + localDateTime.getMonthValue() + "/" + localDateTime.getDayOfMonth();
    }
}
