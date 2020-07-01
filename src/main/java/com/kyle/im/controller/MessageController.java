package com.kyle.im.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@Api(value = "MessageController")
@RequestMapping("/im")
public class MessageController {
    @Resource
    private RabbitTemplate rabbitTemplate;
    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);

    @PostMapping("/test")
    @ResponseBody
    @ApiOperation(value="测试用例，输入字符串作为消息")
    public String test(@ApiParam(value = "消息")@RequestParam String message){
        rabbitTemplate.convertAndSend("im","消息推送");
        return "Hello World";
    }
}
