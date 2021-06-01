/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameserver.objects;

import Simulation.framework.SimulationBody;
import gameserver.ServerMainTest;
import gameserver.enums.FishType;
import gameserver.enums.ObjectType;
import gameserver.packets.ObjectDespawnPacket;
import gameserver.packets.ObjectMovePacket;
import gameserver.utils.Ider;
import gameserver.utils.Rotation;
import gameserver.utils.Utils;
import gameserver.utils.Vector2;
import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.geometry.Geometry;
import org.dyn4j.geometry.MassType;

import java.awt.*;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Khang
 */
public class Fish extends GameObject{
    int health;
    public static Ider fishIder = new Ider();
    int DirectionID;
    FishType ft;
    private ScheduledExecutorService e;

    private SimulationBody b;
    public Fish(Integer id, Vector2 position, Rotation rotation, FishType ft, int DirectionID) {
        super(id, position, rotation);
        spawn();
    }
    
    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
    
    public FishType getType(){
        return ft;
    }
    private void spawn(){

        b = new SimulationBody(new Color(RandomInt(0, 255), RandomInt(0, 255), RandomInt(0, 255)));
        BodyFixture fixture = b.addFixture(Geometry.createCircle(0.2));
        fixture.setRestitutionVelocity(0.0);
        //translate vị trí Spawn
        //Random Toa do
        b.translate(getPosition().x,getPosition().y);

        b.setLinearVelocity(Direction(1));
        //System.out.println("Vec2 "+Rotation.rotation45().toVector());
        // set mass infinite de Object Move lien tuc
        b.setMass(MassType.INFINITE);
        ServerMainTest.mapManager.getGameMap().addBody(b);

        e = Executors.newSingleThreadScheduledExecutor();


        e.scheduleAtFixedRate(()->ServerMainTest.mapManager.getGameMap().addInQueue(this::sendAndSetFishCord), 0, 16, TimeUnit.MILLISECONDS) ;
        e.schedule(() -> ServerMainTest.mapManager.getGameMap().addInQueue(this::dispose), 30000, TimeUnit.MILLISECONDS);

    }
    public synchronized void dispose(){

        if (b != null && b.getFixtureCount() > 0) {
            b.removeAllFixtures();
            fishIder.returnBackID(getId());

            ServerMainTest.mapManager.getGameMap().removeBody(b);
            ServerMainTest.mapManager.objects.remove(getId());

            //TODO: send to clients to delete object
            for (Player p : ServerMainTest.players.values())
            {
                Utils.packetInstance(ObjectDespawnPacket.class , p).write(this.getId(), ObjectType.FISH);
            }

        }

        if(e != null && !e.isShutdown()) e.shutdown();

    }
    public void sendAndSetFishCord(){

        org.dyn4j.geometry.Vector2 fish = b.getTransform().getTranslation();

        setPosition(new Vector2((float)fish.x, (float)fish.y));

        for (Player p : ServerMainTest.players.values())
        {
            Utils.packetInstance(ObjectMovePacket.class, p).write(this.getId(),ObjectType.FISH,(float)fish.x, (float)fish.y,0f);
        }
    }
    public int RandomInt(int min, int max) {
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

    public org.dyn4j.geometry.Vector2 Direction(int RandomDirID) {
        Double speed = 5.0;
        speed = speed * 0.1;
        org.dyn4j.geometry.Vector2 Result = new org.dyn4j.geometry.Vector2(0, 0);
        switch (RandomDirID) {
            case 1:
                // Right 0
                //setMagnitude vận tốc
                //Rotation.rotation45().toVector() Rotation về Vector
                Result = org.dyn4j.geometry.Rotation.rotation0().toVector().setMagnitude(speed);
                return Result;
            case 2:
                // Top Right 45
                Result = org.dyn4j.geometry.Rotation.rotation45().toVector().setMagnitude(speed);
                return Result;
            case 3:
                // Top
                Result = org.dyn4j.geometry.Rotation.rotation90().toVector().setMagnitude(speed);
                return Result;
            case 4:
                // Top Left 135
                // Top Left
                Result = org.dyn4j.geometry.Rotation.rotation135().toVector().setMagnitude(speed);
                return Result;
            case 5:
                //Left
                Result = org.dyn4j.geometry.Rotation.rotation180().toVector().setMagnitude(speed);
                return Result;
            case 6:
                //Left Bot
                Result = org.dyn4j.geometry.Rotation.rotation225().toVector().setMagnitude(speed);
                return Result;
            case 7:
                // Bot
                Result = org.dyn4j.geometry.Rotation.rotation270().toVector().setMagnitude(speed);
                return Result;
            case 8: //Bot Right
                Result = org.dyn4j.geometry.Rotation.rotation315().toVector().setMagnitude(speed);
                return Result;

            default:
                return Result;
        }
    }
}
