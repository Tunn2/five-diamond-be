package online.fivediamond.be.util;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
@Component
public class DateUtils {
    public  String formatDate(String inputDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = dateFormat.parse(inputDate);
            return dateFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public  boolean isExpired(String endDate) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date endDateTime = inputFormat.parse(endDate);
            Date currentDateTime = new Date();

            return currentDateTime.after(endDateTime);
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isNotYet(String startDate) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date startDateTime = inputFormat.parse(startDate);
            Date currentDateTime = new Date();
            return currentDateTime.before(startDateTime);
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }
}
