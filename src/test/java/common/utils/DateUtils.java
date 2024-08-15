package common.utils;

import java.time.ZonedDateTime;

public class DateUtils {
    public int extractYear(String date) {
        return ZonedDateTime.parse(date)
                .getYear();
    }

    public int extractMonth(String date) {
        return ZonedDateTime.parse(date)
                .getMonthValue();
    }

    public int extractDay(String date) {
        return ZonedDateTime.parse(date)
                .getDayOfMonth();
    }
}
