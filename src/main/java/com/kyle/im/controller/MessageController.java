package com.kyle.im.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/im")
public class MessageController {
    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);

    @RequestMapping("/test")
    @ResponseBody
    public String test(){

        return "Hello World";
    }
}
