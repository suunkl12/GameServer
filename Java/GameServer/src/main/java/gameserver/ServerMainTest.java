/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameserver;

import egroup.gameserver.Position;
import gameserver.managers.MapManager;
import gameserver.objects.*;
import gameserver.packets.*;
import gameserver.physics.BanCaPhysics;
import gameserver.utils.GunSlot;
import gameserver.utils.Rotation;
import gameserver.utils.Vector2;
import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;


import io.netty.handler.codec.protobuf.*;
import java.net.InetSocketAddress;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Khang
 */
public class ServerMainTest {

    public static final Float BULLET_SPEED = 0.1f;
    
    public static int TIMEOUT = 5000;
    public static int MaxPlayer = 4;
    private final int port ;
    
    
    private final String host;
    private final int serverPort;
    
    public static HashMap<Integer, Class<? extends Packet>> packets = new HashMap<Integer, Class<? extends Packet>>(){{

        //put(1, PingPacket.class);
        put(2, PlayerSpawnPacket.class);
        put(3, ClientInfoPacket.class);
        put(4, ObjectSpawnPacket.class);
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
    
    public static  MapManager mapManager;
    
    public static List<GunSlot> gunPositions = new ArrayList<>(
            Arrays.asList(
                    new GunSlot(new Vector2(5, -5), false) ,
                    new GunSlot(new Vector2(-5,-5), false),
                    new GunSlot(new Vector2(5,5), false),
                    new GunSlot(new Vector2(-5,5), false)
                    ));
    
    
    public ServerMainTest(int port) {
        this.port = port;
        this.host = "";
        this.serverPort = 25000;
    }

    public ServerMainTest(int port, String host, int serverPort) {
        this.port = port;
        this.host = host;
        this.serverPort = serverPort;
    }

    
    public static void main(String[] args) throws Exception {
        
        
        
        int port;
        int serverPort;
        String host;
        if (args.length != 3) {
            System.err.println("Usage: " + ServerMainTest.class.getSimpleName()
                    + " <port> <serverPort> <host>");
            port = 4296;
            host = "";
            serverPort = 25000;
        }
        else{        
            port = Integer.parseInt(args[0]);
            serverPort = Integer.parseInt(args[1]);
            host = args[2];
        }
        ServerMainTest newServer = new ServerMainTest(port,host,serverPort);
        
        mapManager = new MapManager();
        
        BanCaPhysics simulation = new BanCaPhysics();
        
        mapManager.setGameMap(simulation);
        
        new Thread(simulation::run).start();
        
        new Thread(newServer::startServer).start();
        
    }

    public void startServer() {
        
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(group)
                    .channel(NioServerSocketChannel.class)
                    //.localAddress(new InetSocketAddress(port))
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
                                // set gun to random, exclusive 4
                                int i = r.ints(0, gunPositions.size()).findFirst().getAsInt() ; 
                                if (!gunPositions.get(i).getIsUsed())
                                {
                                    Rotation rot = new Rotation();

                                    //2 and 3 are gun indexes on the top
                                    if(i==2 || i==3){
                                        rot.z = 180f;
                                    }

                                    gunPositions.get(i).setIsUsed(true);
                                    System.out.println("You are in slotGun: " + i);                                   
                                    
                                    p = new Player(id,gunPositions.get(i).getGunPosition() , rot);

                                    p.gunIndex = i;
                                    break;
                                }
                            } while (true);
                            
                            final EchoServerHandler serverHandler = new EchoServerHandler(p);
                            
                            //Ga??n handler cho player
                            p.setHandler(serverHandler);
                            //L??u tr???? player ?????? g????i th??ng ??i????p
                            players.put(id, p);
                            
                            ch.pipeline().addLast(serverHandler);
                            
                        }
                    })
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childOption(ChannelOption.TCP_NODELAY, true)
                    .bind(port).sync().channel().closeFuture().sync();
            
            
            ChannelFuture f = b.bind().sync();
            f.channel().closeFuture().sync();
        } catch (InterruptedException ex) {
            Logger.getLogger(ServerMainTest.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                group.shutdownGracefully().sync();
            } catch (InterruptedException ex) {
                Logger.getLogger(ServerMainTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void startClient() {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress(host, serverPort))
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch)
                                throws Exception {
                            ch.pipeline().addLast(
                                    new EchoClientHandler());
                        }
                    });
            ChannelFuture f;
            try {
                f = b.connect().sync();
                f.channel().closeFuture().sync();
            } catch (InterruptedException ex) {
                Logger.getLogger(ServerMainTest.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } finally {
            try {
                group.shutdownGracefully().sync();
            } catch (InterruptedException ex) {
                Logger.getLogger(ServerMainTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
