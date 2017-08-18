package com.shop.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by meg on 8/18/17.
 */

public class ApplicationUtil {

    static String pattern = "yyyy-MM-dd";
    static SimpleDateFormat format = new SimpleDateFormat(pattern);

    public static Date parseDate(String strDate) throws ParseException {
        return format.parse(strDate);
    }

}
