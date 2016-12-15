package com.demo.DailyDemo;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by linkang on 12/5/16.
 */
public class ddd {

    public static void main(String[] args) throws Exception {

        File file = new File("a.txt");
        String fileToString = FileUtils.readFileToString(file);

        String[] strings = fileToString.split("\\n");
        Map<String, Integer> countMap = new HashMap<>();
        Arrays.stream(strings).forEach(
                s -> {
                    if (countMap.containsKey(s)) {
                        countMap.put(s, countMap.get(s) + 1);
                    } else {
                        countMap.put(s, 1);
                    }
                }
        );

        List<CompareObj> compareObjs = countMap.keySet().stream().map(key -> new CompareObj(key, countMap.get(key))).collect(Collectors.toList());

        compareObjs.sort((a, b) -> b.getCount() - a.getCount());

        compareObjs.stream().filter(o->o.getCount()>100).limit(1000).forEach(System.out::println);

//        System.out.println(countMap.get("115.211.206.83"));

        System.out.println(compareObjs.stream().filter(o -> o.getCount() > 500).count());
    }

    public static void putData(Map map) {
        map.put("aa", "bbb");
    }

}

class CompareObj {
    private String key;
    private int count;

    public CompareObj(String key, int count) {
        this.key = key;
        this.count = count;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return key + "=" + count;
    }
}
