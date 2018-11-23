package com.aiminfocom.tallyfy.utils;

import android.content.Context;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CalenderUtils {
    public static final String format="dd-mm-yyyy";
    public static Date getCurrentSystemDate(Context context)
    {
        Date now=new Date();

        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        System.out.println(df.format(now));
        return  new Date();
    }
}
