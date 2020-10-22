package br.com.sicredieventlist.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateManager {

    public static String formatDateTimePtBr(Date value) {
        return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(value);
    }
}
