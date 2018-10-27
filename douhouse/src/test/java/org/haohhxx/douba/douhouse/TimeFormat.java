package org.haohhxx.douba.douhouse;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeFormat {

    @Test
    public void formatTest() throws ParseException {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = sdf.parse(year+"-"+"10-25 19:55");
        System.out.println(date);
    }

    @Test
    public void testCurTime(){
        String current = new Date(System.currentTimeMillis()-(24*60*60*1000)).toString();
        System.out.println(current);
    }


}
