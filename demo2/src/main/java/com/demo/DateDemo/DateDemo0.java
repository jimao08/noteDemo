package com.demo.DateDemo;

import com.google.common.base.Optional;

import java.util.Calendar;
import java.util.Date;

/**
 * date and optional demo
 * Created by linkang on 10/12/16.
 */
public class DateDemo0 {
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();


        calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        System.out.println(new Date(calendar.getTimeInMillis()));

        System.out.println(Optional.fromNullable(null).or("0"));
        System.out.println((String)null);


        String sval = "123";
    }

}

