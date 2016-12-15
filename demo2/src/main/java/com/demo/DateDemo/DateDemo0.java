package com.demo.DateDemo;

import com.google.common.base.Optional;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * date and optional demo
 * Created by linkang on 10/12/16.
 */
public class DateDemo0 {
    public static void main(String[] args) throws Exception{
        Calendar calendar = Calendar.getInstance();


        calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        System.out.println(new Date(calendar.getTimeInMillis()));

        System.out.println(Optional.fromNullable(null).or("0"));

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM月dd日 hh:mm:ss");
        Date d1 = simpleDateFormat.parse("12月01日 12:01:22");
        System.out.println(d1.getTime());

        System.out.println(simpleDateFormat.format(new Date(1481618637274L)));
    }

}

