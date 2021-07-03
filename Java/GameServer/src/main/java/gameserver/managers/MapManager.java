/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameserver.managers;

import gameserver.enums.ObjectType;
import gameserver.objects.Bullet;
import gameserver.objects.Fish;
import gameserver.objects.GameObject;
import gameserver.objects.Player;
import gameserver.packets.ObjectSpawnPacket;
import gameserver.physics.BanCaPhysics;
import gameserver.utils.Utils;
import java.util.HashMap;

/**
 * Manager dùng để sync vị trí các object trong game
 * @author Khang
 */
public class MapManager {
    
    
    volatile BanCaPhysics gameMap;

    public BanCaPhysics getGameMap() {
        return gameMap;
    }

    public void setGameMap(BanCaPhysics gameMap) {
        this.gameMap = gameMap;
    }

    public HashMap<Integer, GameObject> objects = new HashMap<>();
    public HashMap<Integer, Bullet> bullets = new HashMap<>();
    public static HashMap<Integer,Fish> fishes = new HashMap<>();
    
    public void initialize(){

        gameMap = new BanCaPhysics();
        gameMap.run();

        gameMap.getWorld().getSettings().setStepFrequency(0.001d);

    }

    public synchronized void onConnect(Player p){

        for(GameObject go : objects.values()){

            sendGameObject(p, go);

        }
        for(GameObject b : bullets.values()){
            sendGameObject(p, b);
        }

    }

    public void sendGameObject(Player p, GameObject go){

        ObjectType ot = ObjectType.fromObject(go);

        Utils.packetInstance(ObjectSpawnPacket.class, p).write(
                go.getId()
                , ot
                , Utils.getType(go, ot)
                , go.getPosition().x
                , go.getPosition().y
                ,go.getRotation().z
                , (ot == ObjectType.FISH ? ((Fish) go).getHealth() : 0));

    }

    
    
}
