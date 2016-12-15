package com.demo.FetchDemo;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by linkang on 12/2/16.
 */
public class Fetch2 {

    public static void main(String[] args) {
        final String str = "<img class=\"Avatar SidebarListNav-avatar\" src=\"https://pic4.zhimg.com/2229031a6c5f0d2b262c874169a6cad3_s.png\" srcset=\"https://pic4.zhimg.com/2229031a6c5f0d2b262c874169a6cad3_xs.jpg 2x\" alt=\"参加摄影比赛的正确姿势\" />\n";


        List<String> tmp = new ArrayList<>();
        Pattern pattern = Pattern.compile("https.*?(jpg|png|jpeg|gif)");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            System.out.println(matcher.group());
            tmp.add(matcher.group());
        }

    }
}
