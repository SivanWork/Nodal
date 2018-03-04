package com.example.ranad.nodalsystems.usage;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.util.Log;

import com.example.ranad.nodalsystems.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;


public class DateUtils {

    public static String getCurrentDate() {

        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(new Date());

    }

    public static String getUTCDate(String strDate) {
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(new Date(strDate));
    }

    public static String getNormalDateFromUTC(String strDate) {

        Log.i("DDD",strDate);

        TimeZone utc = TimeZone.getTimeZone("UTC");
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        f.setTimeZone(utc);
        GregorianCalendar cal = new GregorianCalendar(utc);

        try {
            cal.setTime(f.parse(strDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(cal.getTime());




        return cal.getTime().toString();



        //return cal.getTime().toString();
    }
}
