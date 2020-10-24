package br.com.sicredieventlist.util;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.ext.junit.runners.AndroidJUnit4;

@RunWith(AndroidJUnit4.class)
public class NumberManagerTest extends TestCase {

    @Test
    public void testFormatCurrenty() {
        Double value = 15.5;
        String expectedReturn = "R$15,50";
        String result = NumberManager.formatCurrenty(value);
        assertTrue(result.equals(expectedReturn));
    }
}