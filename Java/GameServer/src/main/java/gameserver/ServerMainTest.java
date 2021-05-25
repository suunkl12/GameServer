/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameserver;

import egroup.gameserver.Position;
import gameserver.objects.Player;
import gameserver.packets.*;
import gameserver.utils.GunSlot;
import gameserver.utils.Rotation;
import gameserver.utils.Vector2;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;


import io.netty.handler.codec.protobuf.*;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Khang
 */
public class ServerMainTest {

    public static int TIMEOUT = 5000;
    public static int MaxPlayer = 4;
    private final int port ;
    
    public static HashMap<Integer, Class<? extends Packet>> packets = new HashMap<Integer, Class<? extends Packet>>(){{

        //put(1, PingPacket.class);
        put(2, PlayerSpawnPacket.class);
        put(3, ClientInfoPacket.class);
        //put(4, ObjectSpawnPacket.class);
        //put(5, PlayerMovePacket.class);
        put(6, PlayerShootPacket.class);
        put(7, ObjectDespawnPacket.class);
        //put(8, PlayerInfoPacket.class);
        //put(9, CircleInfoPacket.class);
        //put(10, PlayerPickupPacket.class);
        //put(11, ObjectInfoPacket.class);

    }};
    
    public static HashMap<Integer, Player> players = new HashMap<>();
    public static HashMap<ChannelHandlerContext, EchoServerHandler> handlers = new HashMap<>();
    
    public static List<GunSlot> gunPositions = new ArrayList<>(
            Arrays.asList(
                    new GunSlot(new Vector2(5, -5), false) ,
                    new GunSlot(new Vector2(-5,-5), false),
                    new GunSlot(new Vector2(5,5), false),
                    new GunSlot(new Vector2(-5,5), false)
                    ));
    
    
    public ServerMainTest(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws Exception {
        int port;
        if (args.length != 1) {
            System.err.println("Usage: " + ServerMainTest.class.getSimpleName()
                    + " <port>");
            port = 4296;

        }
        else{        
            port = Integer.parseInt(args[0]);
        }
        new ServerMainTest(port).start();
    }

    public void start() throws Exception {
        
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(group)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(port))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch)
                                throws Exception {
                            ch.pipeline().addLast((new ProtobufVarint32FrameDecoder()));
                            ch.pipeline().addLast(new ProtobufDecoder(HotMessage.Packet.getDefaultInstance()));
                            ch.pipeline().addLast(new ProtobufVarint32LengthFieldPrepender());
                            ch.pipeline().addLast(new ProtobufEncoder());
                            
                            Random r = new Random();
                            Player p;
                            Integer id = Player.ider.next();
                            
                            do
                            {
                            // set gun to random
                            int i = r.ints(0, gunPositions.size() - 1).findFirst().getAsInt() ; 
                            if (!gunPositions.get(i).getIsUsed())
                            {
                                gunPositions.get(i).setIsUsed(true);
                                System.out.println("You are in slotGun: " + i);
                                p = new Player(id,gunPositions.get(i).getGunPosition() , new Rotation());
                                
                                p.gunIndex = i;
                                break;
                            }
                            
                            } while (true);
                            
                            final EchoServerHandler serverHandler = new EchoServerHandler(p);
                            
                            //Gán handler cho player
                            p.setHandler(serverHandler);
                            
                            //Lưu trữ player để gửi thông điệp
                            players.put(id, p);
                            ch.pipeline().addLast(serverHandler);
                            
                        }
                    });
            
            
            ChannelFuture f = b.bind().sync();
            f.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully().sync();
        }
    }
    
    
}
