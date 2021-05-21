/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameserver.packets;

import gameserver.HotMessage;
import gameserver.utils.Utils;
import java.lang.invoke.MethodHandles;

/**
 *
 * @author Khang
 */
public class PlayerSpawnPacket extends Packet{

    
    // static block, dùng để gọi hàm static của class này
    // nếu không có chữ static, chương trình sẽ báo lỗi
    static {
        
        //should debug or write a different program to understand this more
        setTypes(MethodHandles.lookup().lookupClass(),
                Integer.class, // ID
                Float.class, // X - Position
                Float.class, // Y - Position
                Float.class, // Z - Quaternion
                Float.class // W - Quaternion
        );

    }

    public PlayerSpawnPacket() {
        super(Type.PLAYER_SPAWN);
    }

    @Override
    public void write(Object... objects) {
        
        if (!Utils.isItSuitable(getTypes(), objects)) return;

        getChannel().writeAndFlush(HotMessage.Packet
                .newBuilder()
                .setId(getType().getTag())
                .setMsg(Utils.toString(objects))
                .build());

    }

    @Override
    public void read(String in) { }
    
}
