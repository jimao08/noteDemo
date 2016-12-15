package com.demo.FetchDemo;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by linkang on 12/2/16.
 */
public class Fetch3 {
    public static void main(String[] args) {
        String str = "https://static.zhihu.com/static/revved/img/ios/touch-icon-60.9911cffb.png";

        System.out.println(StringUtils.right(str,3));
        System.out.println(str.lastIndexOf("/"));
        System.out.println(str.substring(46,72));
        System.out.println(StringUtils.right(str,str.length() - 1 - str.lastIndexOf("/")));
    }
}
