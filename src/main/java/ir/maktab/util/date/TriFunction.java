package ir.maktab.util.date;

import java.time.LocalDateTime;

@FunctionalInterface
public interface TriFunction {
    boolean test(LocalDateTime testDate,LocalDateTime startDate,LocalDateTime endDate);
}
