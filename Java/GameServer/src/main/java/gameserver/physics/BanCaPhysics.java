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
import org.dyn4j.*;
import org.dyn4j.geometry.AABB;
import org.dyn4j.geometry.Rectangle;
import org.dyn4j.world.World;
/**
 *
 * @author Khang
 */
public class BanCaPhysics extends SimulationFrame {
        public static final double wideMultipler = 1920/7;
        public static final double heightMultipler = 1080/7;
        
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
            
            g.draw(new Rectangle2D.Double(-scale*wideMultipler/2, -scale*heightMultipler/2, scale*wideMultipler, scale*heightMultipler) );
            // g.draw(new Rectangle2D.Double(-50, -50, 100, 100) );
        }

        /**
        * Default constructor.
        */
        public BanCaPhysics() {
               super("BanCa", 1.0);
               
               CustomEventKeyListener keyListener = new CustomEventKeyListener();
               this.addKeyListener(keyListener);
               this.canvas.addKeyListener(keyListener);
         }
        
        // no gravity on a top-down view of a billiards game
        @Override
        protected void initializeWorld() {
            
            this.world.setGravity(World.ZERO_GRAVITY);
            
            
        }
        
        //world của Simulation frame được protected
        public void addBody(SimulationBody body){
            this.world.addBody(body);
        }
        
        public void removeBody(SimulationBody body){
            this.world.removeBody(body);
        }
}
        
        
        
