/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameserver.objects;

import java.util.concurrent.ScheduledExecutorService;
import Simulation.framework.*;
import gameserver.ServerMainTest;
import gameserver.enums.ObjectType;
import gameserver.packets.ObjectDespawnPacket;
import gameserver.packets.ObjectMovePacket;
import gameserver.utils.Ider;
import gameserver.utils.Utils;
import gameserver.utils.Vector2;
import java.awt.Color;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.geometry.Geometry;
import org.dyn4j.geometry.MassType;

/**
 *
 * @author Khang
 */
public class Bullet extends GameObject {

    
    public static Ider bulletIder = new Ider();
    private float rotation;
    private ScheduledExecutorService e;

    private SimulationBody b;

    public Bullet(Integer id, Vector2 position, float rotation) {
        super(id, position);

        this.rotation = rotation;
        spawn();
    }

    private void spawn(){

        b = new SimulationBody(Color.BLACK);
        BodyFixture bf = new BodyFixture(Geometry.createRectangle(0.01295, 0.01295));

        bf.setSensor(true);
        bf.setUserData(this);
        b.addFixture(bf);
        b.setMass(MassType.NORMAL);
        org.dyn4j.geometry.Vector2 g = Utils.degreesToVector2(rotation);
        
        b.translate(getPosition().x+ g.x, getPosition().y  + g.y);
        b.applyForce(Utils.multiply(g, ServerMainTest.BULLET_SPEED));
        
        
        ServerMainTest.mapManager.getGameMap().addBody(b);

        e = Executors.newSingleThreadScheduledExecutor();
        
        
        e.scheduleAtFixedRate(()->ServerMainTest.mapManager.getGameMap().addInQueue(this::sendAndSetBulletCord), 0, 16, TimeUnit.MILLISECONDS) ;
        e.schedule(() -> ServerMainTest.mapManager.getGameMap().addInQueue(this::dispose), 2000, TimeUnit.MILLISECONDS);

    }

    public synchronized void dispose(){

        if (b != null && b.getFixtureCount() > 0) {
            b.removeAllFixtures();
            bulletIder.returnBackID(getId());
            
            ServerMainTest.mapManager.getGameMap().removeBody(b);
            ServerMainTest.mapManager.bullets.remove(getId());
            
            //TODO: send to clients to delete object
            for (Player p : ServerMainTest.players.values())
            {
                Utils.packetInstance(ObjectDespawnPacket.class , p).write(this.getId(),ObjectType.BULLET);
            }
            
        }

        if(e != null && !e.isShutdown()) e.shutdown();

    }
    
    public void sendAndSetBulletCord(){
        
        org.dyn4j.geometry.Vector2 bullet = b.getTransform().getTranslation();
        
        setPosition(new Vector2((float)bullet.x, (float)bullet.y));
        
        for (Player p : ServerMainTest.players.values())
        {
            Utils.packetInstance(ObjectMovePacket.class, p).write(this.getId(),ObjectType.BULLET,(float)bullet.x, (float)bullet.y,0f);
        }
    }


}