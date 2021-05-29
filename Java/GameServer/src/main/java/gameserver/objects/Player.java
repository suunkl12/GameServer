/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameserver.objects;

import gameserver.utils.Rotation;
import gameserver.EchoServerHandler;
import gameserver.ServerMainTest;
import gameserver.enums.ObjectType;
import gameserver.packets.ObjectSpawnPacket;
import gameserver.utils.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Khang
 */
public class Player extends GameObject{
    
    public static Ider ider = new Ider();
    private Long l;
    private EchoServerHandler h;
    public int gunIndex;
    
    private final long SHOOT_COOLDOWN = 400;
    private volatile long currentTime = 0;
    
    private ScheduledExecutorService e;
    
    
    public HashMap<Integer, Integer> staying = new HashMap<>();
    
    public Player(Integer id, Vector2 position, Rotation rotation) {
        super(id, position, rotation);
        
        Player p = this;
        this.l = System.currentTimeMillis() - 1000;
        
        e = Executors.newSingleThreadScheduledExecutor();
        e.scheduleAtFixedRate(() -> {
            if (!ServerMainTest.players.containsKey(p.getId())) {e.shutdown(); return;}

            if (getDifference() >= ServerMainTest.TIMEOUT){

                //Nếu như mất quá lâu để kết nói thì disconnect
                //h.ctx.disconnect();
                //return;
            }
            
            //HashMap<Integer, Integer> map = new HashMap<>();
            
            //List<Integer> remove = new ArrayList<>();
            //for(Integer key : p.staying.keySet()){ if (!map.containsKey(key)) remove.add(key); }
            //for(Integer key : remove){ p.staying.remove(key); }

        }, 100, 100, TimeUnit.MILLISECONDS);
    }
    
    
    
    //Vẽ viên đạn trên map simulation
        
    
    public Player(Integer id, Vector2 position) {
        super(id, position);
    }
    
    public Long getDifference(){
        return System.currentTimeMillis() - l;
    }

    public EchoServerHandler getHandler() {
        return h;
    }
    public void setHandler(EchoServerHandler h){
        this.h = h;
    }
    
    public synchronized  void Shoot(float rotation){
        if(System.currentTimeMillis() < currentTime + SHOOT_COOLDOWN ) return;
        
        Integer id = Bullet.bulletIder.next();
        
        Bullet b = new Bullet(id,getPosition(), rotation+ 90);
        ServerMainTest.mapManager.bullets.put(id, b);
        
        
        for( Player p : ServerMainTest.players.values()){
            //TODO: change the bullet ID
            Utils.packetInstance(ObjectSpawnPacket.class, p).write(
                    id // ID
                    , ObjectType.BULLET // object type
                    ,"" // fish type
                    ,getPosition().x
                    ,getPosition().y
                    ,getRotation().z
                    ,0); 
        }
        
        
        currentTime = System.currentTimeMillis();
    }
    
}
