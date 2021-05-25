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
public class ObjectDespawnPacket extends Packet {

    static {

        setTypes(MethodHandles.lookup().lookupClass(),
                Integer.class, // ID
                ObjectType.class // Type of object
        );

    }

    public ObjectDespawnPacket() {
        super(Type.OBJECT_DESPAWN);
    }

    @Override
    public void write(Object... objects) {
        if (!Utils.isItSuitable(getTypes(), objects)) return;

        getChannel().writeAndFlush(HotMessage.Packet.newBuilder().setId(getType().getTag()).setMsg(Utils.toString(objects)).build());
    }

    @Override
    public void read(String in) { }

}
