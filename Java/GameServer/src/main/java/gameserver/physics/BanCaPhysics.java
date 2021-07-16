/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameserver.physics;

import Simulation.framework.SimulationBody;
import Simulation.framework.SimulationFrame;
import gameserver.enums.FishType;
import gameserver.objects.Bullet;
import gameserver.objects.Fish;
import gameserver.objects.GameObject;
import gameserver.objects.Player;
import gameserver.utils.Rotation;
import gameserver.utils.Vector2;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.dyn4j.*;
import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.dynamics.Settings;
import org.dyn4j.geometry.*;
import org.dyn4j.world.World;

import javax.swing.*;
import org.dyn4j.collision.CollisionBody;
import org.dyn4j.collision.Fixture;
import org.dyn4j.world.BroadphaseCollisionData;
import org.dyn4j.world.ManifoldCollisionData;
import org.dyn4j.world.NarrowphaseCollisionData;
import org.dyn4j.world.listener.CollisionListenerAdapter;

/**
 * @author Khang
 */
public class BanCaPhysics extends SimulationFrame {
    public static final double wideMultipler = 1920 * 8;
    public static final double heightMultipler = 1080 * 8;
    
    public CollisionListenerAdapter customCollision;
    private class CustomEventKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_A:
                    //IsRandomTarget là check xem huong di chuyen co Random ko
                    Fish b = new Fish(1, new Vector2(-7.5f, -3f), new Rotation(0,0), FishType.FOX,false);
                    break;
            }
        }
    }
    
    
    // Gần giống hàm Update Của Unity
    /**
     * Update.
     */
    int i=0;
    int temp=0;
    int waveCount=0;
    
    public class CustomCollision extends CollisionListenerAdapter<CollisionBody<BodyFixture>, BodyFixture>{

        //cheaper
        @Override
        public boolean collision(BroadphaseCollisionData<CollisionBody<BodyFixture>, BodyFixture> collision) {
            on(collision.getFixture1(),collision.getFixture2());
            return true; //To change body of generated methods, choose Tools | Templates.
        }


        
    }
    
    public void on (BodyFixture bf1, BodyFixture bf2){
        Map.Entry<Bullet,GameObject> entry = get(bf1,bf2);
        if (entry == null) return;
        
        if(entry.getValue() instanceof Fish){
            //entry.getKey().dispose();
            //((Fish)entry.getValue()).dispose();
            
            //addInQueue( () -> entry.getKey().dispose() ); là một cách khác để gọi delegate
            addInQueue(entry.getKey()::dispose);
            addInQueue(((Fish)entry.getValue())::dispose);
            
        }
    }
    
    
    public static Map.Entry<Bullet, GameObject> get(BodyFixture bf, BodyFixture bf2){

        if (bf.getUserData() != null && bf.getUserData() instanceof Bullet && bf2.getUserData() != null && isObject(bf2.getUserData())) return new AbstractMap.SimpleEntry<>((Bullet) bf.getUserData(), (GameObject) bf2.getUserData());
        else if (bf2.getUserData() != null && bf2.getUserData() instanceof Bullet && bf.getUserData() != null && isObject(bf.getUserData())) return new AbstractMap.SimpleEntry<>((Bullet) bf2.getUserData(), (GameObject) bf.getUserData());

        return null;

    }
    
    private static Boolean isObject(Object o){

        return o instanceof Fish;

    }
    
    
    
    @Override
    protected void render(Graphics2D g, double elapsedTime) {
        super.render(g, elapsedTime);
        g.setColor(Color.BLACK);
        final double scale = this.getScale();

        //Code Update Here
        //Fish b = new Fish(1, new Vector2(-7.5f, Fish.RandomFloat(-4.5f,4.5f)), new Rotation(0,0), FishType.FOX,2);
        //Fish b = new Fish(1, new Vector2(-7.5f, Fish.RandomFloat(-4.5f,4.5f)), new Rotation(0,0), FishType.FOX,1);
        if(i==temp)
        {temp=500;
            if(waveCount<5)
                for (int j = 0; j < 5; j++) {
                    int id = Fish.fishIder.next();
                    //Fish c = new Fish(1+j+2, new Vector2(-7.5f, Fish.RandomFloat(-4.5f,4.5f)), new Rotation(0,0),Fish.RandomFish(true,FishType.FOX) ,true);
                    Fish c = new Fish(id, new Vector2(-7.5f, Fish.RandomFloat(-4.5f,4.5f)), new Rotation(0,0),Fish.RandomFish(true,FishType.FOX) ,true);
                }
            if(waveCount>5&&waveCount<10)
                for (int j = 0; j < 5; j++) {
                    int id = Fish.fishIder.next();
                    //Fish c = new Fish(1+j+10, new Vector2(7.5f, Fish.RandomFloat(-4.5f,4.5f)), new Rotation(0,0), FishType.CHICKEN,true);
                    Fish c = new Fish(id, new Vector2(7.5f, Fish.RandomFloat(-4.5f,4.5f)), new Rotation(0,0), FishType.CHICKEN,true);
                }
            //từ đây trở đi spawn theo đường thẳng
            // -2+j ở vector2 ở đây là để sấp xếp vị trí trong vòng for
            // isRandomTarget để biết là Object có phải Random hướng di chuyển ko
            if(waveCount>10)
                for (int j = 0; j < 5; j++) {
                    int id = Fish.fishIder.next();
                    //Fish c = new Fish(1+j+10, new Vector2(7.5f, -2+j), new Rotation(0,0), FishType.PIG,false);
                    Fish c = new Fish(id, new Vector2(7.5f, -2+j), new Rotation(0,0), FishType.PIG,false);
                }
            if(waveCount>15&&waveCount<=20)
                for (int j = 0; j < 5; j++) {
                    int id = Fish.fishIder.next();
                    //Fish c = new Fish(1+j+10, new Vector2(-7.5f, -2+j), new Rotation(0,0), FishType.MOUSE,false);
                    Fish c = new Fish(id, new Vector2(-7.5f, -2+j), new Rotation(0,0), FishType.MOUSE,false);

                }
            if(waveCount>20&&waveCount<=25)
                for (int j = 0; j < 5; j++) {
                    int id = Fish.fishIder.next();
                    //Fish c = new Fish(1+j+10, new Vector2(-7.5f, -2+j), new Rotation(0,0), FishType.RABBIT,false);
                    Fish c = new Fish(id, new Vector2(-7.5f, -2+j), new Rotation(0,0), FishType.RABBIT,false);
                }
            if(waveCount>25)
                for (int j = 0; j < 5; j++) {
                    int id = Fish.fishIder.next();
                    //Fish c = new Fish(1+j+10, new Vector2(-7.5f, -2+j), new Rotation(0,0), FishType.CHICKEN,false);
                    Fish c = new Fish(id, new Vector2(-7.5f, -2+j), new Rotation(0,0), FishType.CHICKEN,false);
                }
            if(waveCount>=30)
                waveCount=0;
            waveCount++;
            i=0;
        }
        i++;
        //End Code Update
        // chỉnh độ phân giải màn hình
        g.draw(new Rectangle2D.Double(-wideMultipler / scale / 2, -heightMultipler / scale / 2, wideMultipler / scale, heightMultipler / scale));
        // g.draw(new Rectangle2D.Double(-50, -50, 100, 100) );

    }

    /**
     * Default constructor.
     */
    public BanCaPhysics() {
        super("BanCa", 32.0);

        CustomEventKeyListener keyListener = new CustomEventKeyListener();
        this.addKeyListener(keyListener);
        this.canvas.addKeyListener(keyListener);
    }
    // no gravity on a top-down view of a billiards game
    //Gần Giống hàm Start của Unity

    /**
     * Start.
     */
    @Override
    protected void initializeWorld() {
        this.world.setGravity(World.ZERO_GRAVITY);
        
        customCollision = new CustomCollision();
        
        this.world.addCollisionListener(customCollision);
    }

    

    //world của Simulation frame được protected
    public void addBody(SimulationBody body) {
        this.world.addBody(body);
    }

    public void removeBody(SimulationBody body) {
        this.world.removeBody(body);
    }

    public World<SimulationBody> getWorld() {
        return world;
    }
}