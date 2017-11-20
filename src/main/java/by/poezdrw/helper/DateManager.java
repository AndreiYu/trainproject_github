package by.poezdrw.helper;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateManager {

    private static final Logger LOGGER = LogManager.getLogger(DateManager.class);

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");

    public static String getTomorrowDate() {

        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date tomorrow = calendar.getTime();

        return DATE_FORMAT.format(tomorrow);
    }

    public static Calendar getDateFromString(String date) {

        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(DATE_FORMAT.parse(date));
        } catch (ParseException e) {
            LOGGER.log(Level.ERROR, "Invalid date provided for testing. Date Format: " + e);
            throw new RuntimeException("Invalid date provided for testing. Check Date Format " + e);
        }
        return calendar;
    }

    public static String getStringFromDate(Calendar calendar) {
        return DATE_FORMAT.format(calendar.getTime());
    }

}
