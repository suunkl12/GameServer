/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameserver.objects;

import gameserver.EchoServerHandler;
import gameserver.utils.*;
import java.util.concurrent.ScheduledExecutorService;

/**
 *
 * @author Khang
 */
public class Player extends GameObject{
    
    public static Ider ider = new Ider();

    private ScheduledExecutorService e;
    private EchoServerHandler h;
    
    public Player(Integer id, Vector2 position, Rotation rotation) {
        super(id, position, rotation);
    }
    
    public Player(Integer id, Vector2 position) {
        super(id, position);
    }
}
