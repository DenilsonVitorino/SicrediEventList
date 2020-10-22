package br.com.sicredieventlist.util;

import java.text.DecimalFormat;

public class NumberManager {

    public static String formatCurrenty(Double value) {
        return new DecimalFormat("R$0.00").format(value);
    }
}
