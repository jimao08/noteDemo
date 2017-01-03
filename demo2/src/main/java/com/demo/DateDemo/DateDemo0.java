package com.demo.DateDemo;

import com.google.common.base.Optional;

import java.text.SimpleDateFormat;
import java.util.*;

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

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d1 = simpleDateFormat.parse("2016-12-28 16:37:0");
        System.out.println(d1.getTime());

//        System.out.println(simpleDateFormat.format(new Date(1482508800000L)));
//        System.out.println(simpleDateFormat.format(new Date(1482681600000L)));
//        System.out.println(simpleDateFormat.format(new Date(1482388115000L)));


        System.out.println(simpleDateFormat.format(new Date(1470723905000L)));


        Map m = new HashMap<>();
        m.put("11", "1232");
        System.out.println(m.getOrDefault("hah","jj"));

    }

}

