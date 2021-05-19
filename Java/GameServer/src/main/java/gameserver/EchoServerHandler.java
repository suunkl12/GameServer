/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameserver;

import com.google.protobuf.MessageLite;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.util.CharsetUtil;

/**
 *
 * @author Khang
 */
@Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(
        "Channel activated ");
    }
    
    
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
    //ByteBuf in = (ByteBuf) msg;
    if( !(msg instanceof  HotMessage.Package ))
    {
        System.out.println("Not a package ");
        return;
    }
    
    System.out.println(
    "Server received: package +" + msg.toString());  //+ in.toString(CharsetUtil.UTF_8));
    
    ctx.write(msg);
    }
    
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)
        .addListener(ChannelFutureListener.CLOSE);
    }
    
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(
        "Channel inactivated ");
    }
    
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,
    Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
