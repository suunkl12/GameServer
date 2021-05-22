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
public class ClientInfoPacket extends Packet {

    static {

        setTypes(MethodHandles.lookup().lookupClass(),
                Integer.class, // ID
                Integer.class // Objects number
        );

    }

    public ClientInfoPacket() {
        super(Type.CLIENT_INFO);
    }

    @Override
    public void write(Object... objects) {
        if (!Utils.isItSuitable(getTypes(), objects)) return;

        getChannel().writeAndFlush(HotMessage.Packet.newBuilder().setId(getType().getTag()).setMsg(Utils.toString(objects)).build());

    }

    @Override
    public void read(String in) { }

}