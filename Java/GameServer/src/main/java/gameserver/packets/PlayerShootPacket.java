/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameserver.packets;

import gameserver.HotMessage;
import gameserver.ServerMainTest;
import gameserver.enums.ObjectType;
import gameserver.objects.Bullet;
import gameserver.objects.Player;
import gameserver.utils.Rotation;
import gameserver.utils.Utils;
import java.lang.invoke.MethodHandles;
import java.util.List;


/**
 *
 * @author Khang
 */
public class PlayerShootPacket extends Packet{

    //Tại sao không xài PlayerShootPacket.class cho lẹ?
    static{
        setTypes( MethodHandles.lookup().lookupClass(),
                Integer.class ,// player ID
                Float.class //degree
                );
    }

    public PlayerShootPacket() {
        super(Type.PLAYER_SHOT);
    }
    
    
    @Override
    public void read(String in) {
        if (!Utils.isItSuitable(getTypes(), in)) return;
        
        
        List<Object> objects = Utils.fromString(getTypes(), in);
        
        //Check xem trong số player có Id của người chơi này không
        if (!ServerMainTest.players.containsKey((int)objects.get(0))
                || objects.get(0) != getPlayer().getId()) //Check xem player truyên vào packet có id giống với player id của packet không
            return; // nếu không thì return
        
        
        //Set rotation của player giống với rotation của client
        getPlayer().setRotation(new Rotation((float)objects.get(1), 0));
        
        getPlayer().Shoot((float)objects.get(1));
        
        for(Player p : ServerMainTest.players.values()){
            //Truyền đến tất cả người chơi khác là người chơi này bắn, kể cả người bắn,
            //Sau khi nhận được packet này, client mới bắt đầu bắn đạn
            //System.out.println("Sending player " + p.getId() + " shoot command");
            Utils.packetInstance(PlayerShootPacket.class, p).write(objects.toArray());
            
            
        }
        
    }
    
    @Override
    public void write(Object... objects) {
        if(!Utils.isItSuitable(getTypes(), objects)) return;
        
        getChannel().writeAndFlush(HotMessage.Packet.newBuilder().setId(getType().getTag()).setMsg(Utils.toString(objects)).build());
    }

    
    
}
