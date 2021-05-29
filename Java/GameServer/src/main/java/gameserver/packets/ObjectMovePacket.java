/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameserver.packets;

import gameserver.HotMessage;
import gameserver.enums.ObjectType;
import gameserver.packets.Packet.Type;
import gameserver.utils.Utils;
import java.lang.invoke.MethodHandles;

/**
 *
 * @author Khang
 */
public class ObjectMovePacket extends Packet{
    
    static{
        setTypes(MethodHandles.lookup().lookupClass(), 
                Integer.class, // ID
                ObjectType.class, //Object type
                Float.class, //position x   
                Float.class, //position y
                Float.class //rotation
        );
    }

    public ObjectMovePacket() {
        super(Type.OBJECT_MOVE);
    }

    @Override
    public void write(Object... objects) {
        if(!Utils.isItSuitable(getTypes(), objects)) return;
        
        getChannel().writeAndFlush(HotMessage.Packet.newBuilder()
                .setId(getType().getTag())
                .setMsg(Utils.toString(objects))
                .build()); // nhớ build để tạo packet
    }

    @Override
    public void read(String in) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
