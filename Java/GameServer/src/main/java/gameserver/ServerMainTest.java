/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameserver;

import gameserver.objects.Player;
import gameserver.objects.Rotation;
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
import java.util.HashMap;

/**
 *
 * @author Khang
 */
public class ServerMainTest {

    public static int TIMEOUT = 5000;
    
    private final int port;
    
    
    
    public static HashMap<Integer, Player> players = new HashMap<>();
    public static HashMap<ChannelHandlerContext, EchoServerHandler> handlers = new HashMap<>();
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
                            
                            Integer id = Player.ider.next();
                            
                            Player p = new Player(id,new Vector2() , new Rotation());
                            
                            final EchoServerHandler serverHandler = new EchoServerHandler(p);
                            
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
