package com.xingoo.test.controller;

import com.xingoo.test.common.Cache;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping("/test2")
    public Long test2(){
        return System.currentTimeMillis();
    }
}
