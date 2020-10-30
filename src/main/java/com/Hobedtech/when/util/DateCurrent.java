package com.Hobedtech.when.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * When Created by furkanansin on Oct, 2020
 */
public class DateCurrent {

    public String getDate() {
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        return timeStamp;
    }


}
