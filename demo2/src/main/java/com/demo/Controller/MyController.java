package com.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by linkang on 10/9/16.
 */

@RestController
@RequestMapping(value = "test")
public class MyController {

    @Autowired
    private MyService myService;

    @RequestMapping(value = "dd", produces = MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.GET)
    public Object mm() {
        Map map = new HashMap();
        map.put("msg", "ss");
        myService.print();
        return map;
    }

    @RequestMapping(value = "")
    public void dd() {

    }
}
