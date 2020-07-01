package com.kyle.im.server;

import java.io.IOException;
import java.net.InetSocketAddress;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

/**
 * I/O数据读写处理类
 *
 */
public class BootNettyChannelInboundHandlerAdapter extends ChannelInboundHandlerAdapter{


    /**
     * 从客户端收到新的数据时，这个方法会在收到消息时被调用
     *
     * @param ctx
     * @param msg
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception, IOException
    {
        System.out.println("channelRead:read msg:"+msg.toString());
        //回应客户端
        String message = (String) msg;
        try {

            System.out.println(message);
            if (message.equals("_bye_")){
                BootNettyServer.clients.remove(ctx.channel());
                ctx.close();
            }else {
                BootNettyServer.clients.writeAndFlush("Server Received Message: "+msg+"\n");
            }
//            System.out.println(buf.refCnt());
//            ctx.writeAndFlush(msg);
        }finally {
            if (message!=null){
//                ReferenceCountUtil.release(buf);
//                System.out.println(buf.refCnt());
            }
        }
//        ctx.writeAndFlush("I got it\n");
    }

    /**
     * 从客户端收到新的数据、读取完成时调用
     *
     * @param ctx
     */
//    @Override
//    public void channelReadComplete(ChannelHandlerContext ctx) throws IOException
//    {
//        System.out.println("channelReadComplete");
//        BootNettyServer.clients.writeAndFlush("Received message");
//        ctx.writeAndFlush("I got it");
//        ctx.flush();
//    }

    /**
     * 当出现 Throwable 对象才会被调用，即当 Netty 由于 IO 错误或者处理器在处理事件时抛出的异常时
     *
     * @param ctx
     * @param cause
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws IOException
    {
        System.out.println("exceptionCaught");
        cause.printStackTrace();
        ctx.close();//抛出异常，断开与客户端的连接
    }

    /**
     * 客户端与服务端第一次建立连接时 执行
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception, IOException
    {
        BootNettyServer.clients.add(ctx.channel());
        super.channelActive(ctx);
        ctx.channel().read();
        InetSocketAddress insocket = (InetSocketAddress) ctx.channel().remoteAddress();
        String clientIp = insocket.getAddress().getHostAddress();
        //此处不能使用ctx.close()，否则客户端始终无法与服务端建立连接
        System.out.println("channelActive:"+clientIp+ctx.name());
        BootNettyServer.clients.writeAndFlush("李柯为我知道是你\n");

    }

    /**
     * 客户端与服务端 断连时 执行
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception, IOException
    {
        super.channelInactive(ctx);
        InetSocketAddress insocket = (InetSocketAddress) ctx.channel().remoteAddress();
        String clientIp = insocket.getAddress().getHostAddress();
        ctx.close(); //断开连接时，必须关闭，否则造成资源浪费，并发量很大情况下可能造成宕机
        System.out.println("channelInactive:"+clientIp);
    }

    /**
     * 服务端当read超时, 会调用这个方法
     *
     * @param ctx
     * @param evt
     * @throws Exception
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception, IOException
    {
        super.userEventTriggered(ctx, evt);
        InetSocketAddress insocket = (InetSocketAddress) ctx.channel().remoteAddress();
        String clientIp = insocket.getAddress().getHostAddress();
        ctx.close();//超时时断开连接
        System.out.println("userEventTriggered:"+clientIp);
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception{
        System.out.println("channelRegistered");
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception{
        System.out.println("channelUnregistered");
    }

    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception{
        System.out.println("channelWritabilityChanged");
    }

}
