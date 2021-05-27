/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameserver.packets;

import gameserver.HotMessage;
import gameserver.enums.ObjectType;
import gameserver.utils.Utils;
import java.lang.invoke.MethodHandles;

/**
 *
 * @author Khang
 */
public class ObjectSpawnPacket extends Packet{

    static {

        setTypes(MethodHandles.lookup().lookupClass(),
                Integer.class, // ID
                ObjectType.class, // Type of object
                String.class, // Fish Type
                Float.class, // X - Position
                Float.class, // Y - Position
                Float.class, // rotation in float
                Integer.class // Health for destructible object
        );

    }
    // Đừng truyền Type vào, getConstructor của Utils sẽ báo lỗi không tìm thấy method
    // public ObjectSpawnPacket(Type t) 
    public ObjectSpawnPacket() {
        super(Type.OBJECT_SPAWN);
    }

    @Override
    public void write(Object... objects) {
        if(!Utils.isItSuitable(getTypes(), objects)) return;
        
        getChannel().writeAndFlush(HotMessage.Packet.newBuilder().setId(getType().getTag()).setMsg(Utils.toString(objects)).build());
    }

    @Override
    public void read(String in) {
        throw new UnsupportedOperationException("Object spawn not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
