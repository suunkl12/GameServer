/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameserver.physics;

import Simulation.framework.SimulationFrame;
import org.dyn4j.*;
import org.dyn4j.world.World;
/**
 *
 * @author Khang
 */
public class BanCaPhysics extends SimulationFrame {

        /**
        * Default constructor.
        */
        public BanCaPhysics() {
               super("Billiards", 250.0);
         }
        // no gravity on a top-down view of a billiards game
        @Override
        protected void initializeWorld() {
            
            this.world.setGravity(World.ZERO_GRAVITY);
            
        }

        /**
        * Entry point for the example application.
        * @param args command line arguments
        */
       public static void main(String[] args) {
               BanCaPhysics simulation = new BanCaPhysics();
               simulation.run();
       }
}
