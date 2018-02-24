package com.example.ranad.nodalsystems.usage;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;

import com.example.ranad.nodalsystems.R;



public class DialogUtils {
    private static TransparentProgressDialog pd;
    private static Handler h;
    private static Runnable r;


    public static ProgressDialog progressDialog = null;

    public static void alertDialog(Context c, String title, String msg, int theme) {


        if (theme == 1) theme = R.style.Theme_AppCompat_DayNight_Dialog;
        else theme = R.style.Theme_AppCompat_DayNight_Dialog_Alert;


        android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(
                c, R.style.Theme_AppCompat_DayNight_Dialog);
        alertDialogBuilder.setTitle(title);

        // set dialog message
        alertDialogBuilder
                .setMessage(msg)
                .setCancelable(true)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        android.app.AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public static ProgressDialog progressDialog(Context c, String title, String msg) {
        progressDialog = new ProgressDialog(c);

        progressDialog.setTitle(title);
        progressDialog.setMessage(msg);
        progressDialog.setCancelable(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(true);
//        progressDialog.show();

        return progressDialog;

    }

    public static void dismissProgress(ProgressDialog progressDialog) {
        progressDialog.dismiss();
    }

    public static TransparentProgressDialog progressWheel(Context c ) {
        h = new Handler();
        pd = new TransparentProgressDialog(c, R.drawable.progresswheel4);
        r =new Runnable() {
            @Override
            public void run() {
                if (pd.isShowing()) {
                    //  pd.dismiss();
                }
            }
        };

        pd.show();
        h.post(r);

        return  pd;

    }

    public static void dismissProgressWheel(TransparentProgressDialog tp ) {
       tp.dismiss();

    }



}
