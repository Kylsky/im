package com.kyle.im.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;
import sun.net.www.content.text.Generic;

import javax.annotation.Resource;

@Component
public class RabbitHandler extends ChannelInboundHandlerAdapter implements MessageHandler{

    @RabbitListener(queues = "im")
    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        GenericMessage message1 = (GenericMessage) message;
        BootNettyServer.clients.writeAndFlush(message1.getPayload()+"\n");
        System.out.println(message);
    }
}
