package br.com.sicredieventlist.util;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.test.ext.junit.runners.AndroidJUnit4;

@RunWith(AndroidJUnit4.class)
public class DateManagerTest extends TestCase {

    @Test
    public void textFormatDateTimePtBr() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String expectedReturn = "23/10/2020 15:00:00";
        try {
            Date date = simpleDateFormat.parse("23/10/2020 15:00:00");
            String result = DateManager.formatDateTimePtBr(date);
            assertTrue(result.equals(expectedReturn));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}