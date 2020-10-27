package br.com.sicredieventlist.util;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

public class DialogManager {
    public static void showMessage(Context context, String value) {
        Toast toast = Toast.makeText( context, value, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
