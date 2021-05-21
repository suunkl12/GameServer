/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameserver.packets;

import gameserver.HotMessage;
import gameserver.utils.Utils;

/**
 *
 * @author Khang
 */
public class PlayerSpawnPacket extends Packet{

    public PlayerSpawnPacket(Type t) {
        super(t);
    }

    @Override
    public void write(Object... objects) {
        
        if (!Utils.isItSuitable(getTypes(), objects)) return;

        getChannel().writeAndFlush(HotMessage.Packet.newBuilder().setId(getType().getTag()).setMsg(Utils.toString(objects)).build());

    }

    @Override
    public void read(String in) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
