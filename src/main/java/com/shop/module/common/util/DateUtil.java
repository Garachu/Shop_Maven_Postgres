package com.shop.module.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by meg on 8/18/17.
 */

public class DateUtil {

    private static final SimpleDateFormat dateFormat
            = new SimpleDateFormat("yyyy-MM-dd");

    public static Date parseDate(String strDate) throws ParseException {
        return dateFormat.parse(strDate);
    }


    public static String formatDate(Date date, String timezone) {
        //dateFormat.setTimeZone(TimeZone.getTimeZone(timezone));
        return dateFormat.format(date);
    }

}
