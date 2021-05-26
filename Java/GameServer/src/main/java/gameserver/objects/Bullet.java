/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameserver.objects;

import java.util.concurrent.ScheduledExecutorService;
import Simulation.framework.*;
import gameserver.ServerMainTest;
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
        BodyFixture bf = new BodyFixture(Geometry.createRectangle(0.1295, 0.1295));

        bf.setSensor(true);
        bf.setUserData(this);
        b.addFixture(bf);
        b.setMass(MassType.NORMAL);
        org.dyn4j.geometry.Vector2 g = Utils.degreesToVector2(rotation);
        b.translate(getPosition().x + g.x, getPosition().y + g.y);
        b.applyForce(Utils.multiply(g, ServerMainTest.BULLET_SPEED));
        
        
        ServerMainTest.mapManager.getGameMap().addBody(b);

        e = Executors.newSingleThreadScheduledExecutor();
        e.schedule(() -> ServerMainTest.mapManager.getGameMap().addInQueue(this::dispose), 1000, TimeUnit.MILLISECONDS);

    }

    public void dispose(){

        if (b != null && b.getFixtureCount() > 0) {
            b.removeAllFixtures();
            ServerMainTest.mapManager.getGameMap().removeBody(b);
        }

        if(e != null && !e.isShutdown()) e.shutdown();

    }


}