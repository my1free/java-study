package com.michealyang.spring.boot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author michealyang
 * @version 1.0
 * @created 18/5/3
 * 开始眼保健操： →_→  ↑_↑  ←_←  ↓_↓
 */
@Controller
public class BaseController {
    private static final Logger logger = LoggerFactory.getLogger(BaseController.class);
    private int oneM = 1024 * 1024;

    @RequestMapping("/")
    @ResponseBody
    String home() {
        logger.info("home");
        return "Hello World!";
    }

    @RequestMapping("/mem")
    @ResponseBody
    String mem(Integer size) {
        logger.info("home");
        //每次申请1M内存
        byte[] mem1 = new byte[(size == null ? 1 : size) * oneM];
        return String.format("申请了%dM内存", size);
    }

}
