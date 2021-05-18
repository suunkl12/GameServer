package egroup.gameserver;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;


/**
 * Created by prog on 12.03.15.
 */
public class ServerMain {
    public static boolean DEBUG = false;
    public static short TCP_PORT = 4296;
    public static int TIMEOUT = 5000;
    public static int PING_TIME = 200;
    
    
    
    public static void main(String[] args){
        
    }
    
    private void tcp(){
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try{
            
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup,workerGroup);
        }
        catch(Exception ex){
            
        }
    }
}
