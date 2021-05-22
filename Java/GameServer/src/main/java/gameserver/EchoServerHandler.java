/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameserver;

import com.google.protobuf.MessageLite;
import gameserver.enums.ObjectType;
import gameserver.objects.Player;
import gameserver.packets.*;


import gameserver.utils.Utils;
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
    public Player p;
    public ChannelHandlerContext ctx = null;
    
    public EchoServerHandler(Player p) {
        this.p = p;
    }
    
    
    
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        this.ctx = ctx;
        if(ServerMainTest.players.size() >= ServerMainTest.MaxPlayer) return;
        
        System.out.println ("Player " + p.getId () + " connected to the server!");
        
        //p là người dùng mà nó sẽ gửi đến, mỗi player có một Handler mà Utils sẽ gán cho packet để gửi thông tin
        Utils.packetInstance(ClientInfoPacket.class,p).write(p.getId(),0);
        
        // PLAYERS
         for (Player o: ServerMainTest.players.values ()) {

             // SENDING EVERYONE ABOUT THE NEW PLAYER'S SPAWN
             Utils.packetInstance (PlayerSpawnPacket.class, o) .write (p.getId (), p.getPosition ().x, p.getPosition ().y, p.getRotation ().z, p.getRotation ().w);

             // SENDING THE NEW PLAYER ABOUT OTHER PLAYERS  THAN YOURSELF (packet above is sent)
             if (o == p) continue;

             Utils.packetInstance (PlayerSpawnPacket.class, p) .write (o.getId (), o.getPosition ().x, o.getPosition ().y, o.getRotation ().z, o.getRotation ().w);

         }
    }
    
    
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
    //ByteBuf in = (ByteBuf) msg;
    if( !(msg instanceof  HotMessage.Packet ))
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
        ctx.flush();
        //ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)
        //.addListener(ChannelFutureListener.CLOSE);
    }
    
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
         System.out.println ("Player " + p.getId () + " disconnected from the server!");
         
         
         //Despawn tất cả người chơi cho người chơi này, bởi vì người chơi này disconnect
         for(Player p : ServerMainTest.players.values()){

            Utils.packetInstance(ObjectDespawnPacket.class, p).write(this.p.getId(), ObjectType.PLAYER);

        }
         
        ServerMainTest.gunPositions.get(p.gunIndex).setIsUsed(false);
        ServerMainTest.handlers.remove(ctx);
        ServerMainTest.players.remove(p.getId());
        Player.ider.add(p.getId());
    }
    
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,
    Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
