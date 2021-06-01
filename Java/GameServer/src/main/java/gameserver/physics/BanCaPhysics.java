/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameserver.physics;

import Simulation.framework.SimulationBody;
import Simulation.framework.SimulationFrame;
import gameserver.objects.Bullet;
import gameserver.utils.Vector2;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.dyn4j.*;
import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.dynamics.Settings;
import org.dyn4j.geometry.*;
import org.dyn4j.world.World;
/**
 *
 * @author Khang
 */
public class BanCaPhysics extends SimulationFrame {
        public static final double wideMultipler = 1920*8;
        public static final double heightMultipler = 1080*8;
        
        private class CustomEventKeyListener  extends KeyAdapter{

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()){
                case KeyEvent.VK_A:
                    Bullet b =  new Bullet(1, new Vector2(0, 0),-90);
                    break;
                }
            }
        }

        
        
        
        @Override
        protected void render(Graphics2D g, double elapsedTime) {
            super.render(g, elapsedTime);


            g.setColor(Color.BLACK);
            final double scale = this.getScale();

            // chỉnh độ phân giải màn hình
            
            g.draw(new Rectangle2D.Double(-wideMultipler/scale/2, -heightMultipler/scale/2, wideMultipler/scale, heightMultipler/scale) );
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
        @Override
        protected void initializeWorld() {
            this.world.setGravity(World.ZERO_GRAVITY);
            int temp=0;
            for (int i = 0; i <= 1000; i++) {System.out.println(i);

                if(i==temp){
                    System.out.println("x:"+i);
                    SimulationBody cueBall = new SimulationBody(new Color(RandomInt(0,255), RandomInt(0,255), RandomInt(0,255)));
                    BodyFixture fixture = cueBall.addFixture(Geometry.createCircle(0.2));
                    fixture.setRestitutionVelocity(0.0);
                    //translate vị trí Spawn
                    //Random Toa do
                    cueBall.translate(RandomFloat(-7.5f, 7.5f), RandomFloat(-4,4));
                    //setMagnitude vận tốc
                    //Rotation.rotation45().toVector() Rotation về Vector
                    cueBall.setLinearVelocity(Direction(RandomInt(1,8)));
                    //System.out.println("Vec2 "+Rotation.rotation45().toVector());
                    // set mass infinite de Object Move lien tuc
                    cueBall.setMass(MassType.INFINITE);
                    this.world.addBody(cueBall);

                    //
                    //========End Moving Ball=======//
                    temp=temp+1;
                }

            }

        }
    public int RandomInt(int min, int max){
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt(max - min + 1) + min;

        return randomNum;
    }
    public static float RandomFloat(float min, float max) {
        Random rand = new Random();
        return rand.nextFloat() * (max - min) + min;
    }

    public org.dyn4j.geometry.Vector2 Direction(int RandomDirID){
        Double speed=5.0;
        speed=speed*0.1;
        org.dyn4j.geometry.Vector2 Result = new org.dyn4j.geometry.Vector2(0,0);
        switch (RandomDirID) {
            case 1:
                // Right 0

                Result= Rotation.rotation0().toVector().setMagnitude(speed);
                return Result;
            case 2:
                // Top Right 45
                Result=Rotation.rotation45().toVector().setMagnitude(speed);
                return Result;
            case 3:
                // Top
                Result=Rotation.rotation90().toVector().setMagnitude(speed);
                return Result;
            case 4:
                // Top Left 135
                // Top Left
                Result=Rotation.rotation135().toVector().setMagnitude(speed);
                return Result;
            case 5:
                //Left
                Result=Rotation.rotation180().toVector().setMagnitude(speed);
                return Result;
            case 6:
                //Left Bot
                Result=Rotation.rotation225().toVector().setMagnitude(speed);
                return Result;
            case 7:
                // Bot
                Result=Rotation.rotation270().toVector().setMagnitude(speed);
                return Result;
            case 8: //Bot Right
                Result=Rotation.rotation315().toVector().setMagnitude(speed);
                return Result;

            default:
                return Result;
        }
    }
        //world của Simulation frame được protected
        public void addBody(SimulationBody body){
            this.world.addBody(body);
        }
        
        public void removeBody(SimulationBody body){
            this.world.removeBody(body);
        }
        
        public World<SimulationBody> getWorld(){
            return world;
        }
}
        
        
        
