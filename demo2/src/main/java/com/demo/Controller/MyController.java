package com.demo.Controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by linkang on 10/9/16.
 */

@RestController
@RequestMapping(value = "test")
public class MyController {

    @RequestMapping(value = "dd", produces = MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.GET)
    public Object mm() {
        Map map = new HashMap();
        map.put("msg", "ss");
        return map;
    }
}
