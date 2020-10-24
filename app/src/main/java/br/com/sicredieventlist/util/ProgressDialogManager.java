package br.com.sicredieventlist.util;

import android.app.ProgressDialog;

import br.com.sicredieventlist.R;

public class ProgressDialogManager {

    public static void wait(ProgressDialog progressDialog) {
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
    }
}
