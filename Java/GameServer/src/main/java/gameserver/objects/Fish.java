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
    FishType ft;
    private ScheduledExecutorService e;
    boolean isRandomTarget;
    private SimulationBody b;
    public Fish(Integer id, Vector2 position, Rotation rotation, FishType ft, boolean isRandomTarget) {
        super(id, position, rotation);
        this.ft = ft;
        this.isRandomTarget = isRandomTarget;
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

        b = new SimulationBody(ft.getColor());
        BodyFixture fixture = b.addFixture(Geometry.createCircle(0.2));
        fixture.setRestitutionVelocity(0.0);
        //translate vị trí Spawn
        //Random Toa do
        b.translate(getPosition().x,getPosition().y);

        b.setLinearVelocity(Direction(getPosition().x,getPosition().y));
        //System.out.println("Vec2 "+Rotation.rotation45().toVector());
        // set mass infinite de Object Move lien tuc
        b.setMass(MassType.INFINITE);
        ServerMainTest.mapManager.getGameMap().addBody(b);

        e = Executors.newSingleThreadScheduledExecutor();


        e.scheduleAtFixedRate(()->ServerMainTest.mapManager.getGameMap().addInQueue(this::sendAndSetFishCord), 0, 16, TimeUnit.MILLISECONDS) ;
        e.schedule(() -> ServerMainTest.mapManager.getGameMap().addInQueue(this::dispose), 33000, TimeUnit.MILLISECONDS);

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
    public static int RandomInt(int min, int max) {
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
    Double speed = 5.0*0.1;
    org.dyn4j.geometry.Vector2 Result = new org.dyn4j.geometry.Vector2(7.5f, -4.5f).setMagnitude(speed);
    public org.dyn4j.geometry.Vector2 Direction(float xPos,float yPos) {
        if(isRandomTarget==true) {
            //Vi Tri Spawn Left Top
            if (xPos < 0 && yPos > 0) {
                Result = new org.dyn4j.geometry.Vector2(7.5f, RandomFloat(-4.5f, 0)).setMagnitude(speed);
            }//Vi Tri Spawn Left Bot
            if (xPos < 0 && yPos < 0) {
                Result = new org.dyn4j.geometry.Vector2(7.5f, RandomFloat(0, 4.5f)).setMagnitude(speed);
            }//Vi Tri Spawn Right Top
            if (xPos > 0 && yPos > 0) {
                Result = new org.dyn4j.geometry.Vector2(-7.5f, RandomFloat(-4.5f, 0)).setMagnitude(speed);
            }//Vi Tri Spawn Right Bot
            if (xPos > 0 && yPos < 0) {
                Result = new org.dyn4j.geometry.Vector2(-7.5f, RandomFloat(0, 4.5f)).setMagnitude(speed);
            }
        }else {
            //LEFT TO RIGHT
            // ly cho yPos - yPos = 0 là để cho nó spawn theo duong thang vì result ở đay trả về hướng đi

            if (xPos < 0) {
                Result = new org.dyn4j.geometry.Vector2(7.5f, yPos-yPos).setMagnitude(speed);
            }//RIGHT TO LEFT
            if (xPos > 0) {
                Result = new org.dyn4j.geometry.Vector2(-7.5f, yPos-yPos).setMagnitude(speed);
            }
        }
        return Result;
    }
    public static FishType RandomFish(boolean Check,FishType Ifnot){
        int temp= Fish.RandomInt(1,5);
        if (Check == true) {
            if(temp ==1){
                return FishType.FOX;
            }
            if(temp ==2){
                return FishType.RABBIT;
            }
            if(temp ==3){
                return FishType.MOUSE;
            }
            if(temp ==4){
                return FishType.PIG;
            }
            if(temp ==5){
                return FishType.CHICKEN;
            }
        }
        return Ifnot;
    }
}
