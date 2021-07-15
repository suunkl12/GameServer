/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameserver.utils;

import gameserver.HotMessage;
import gameserver.ServerMainTest;
import gameserver.enums.ObjectType;
import gameserver.objects.*;
import gameserver.packets.Packet;
import java.util.ArrayList;
import java.util.List;

import org.dyn4j.dynamics.*;
/**
 *
 * @author Khang
 */
public class Utils {
    
    /**
     * Create a packet Instance with the Packet class needed to send information to client,
     * Like PlayerShoot packet or PlayerQuit packet
     */
    public static Packet packetInstance(Class<? extends Packet> c, Player p){

        try {
            //Get constructor của Packet type tương ứng
            //Ví dụ, nếu c là PlayerSpawnPacket, nó sẽ lấy constructor của Packet loại đó
            Packet pt = c.getConstructor().newInstance();
            pt.setChannel(p.getHandler().ctx);
            pt.setPlayer(p);
            return pt;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }
    
    public static org.dyn4j.geometry.Vector2 multiply(org.dyn4j.geometry.Vector2 v, float m){

        v.x *= m;
        v.y *= m;
        return v;

    }
    
    public static org.dyn4j.geometry.Vector2 degreesToVector2(float degrees){

        float radians = (float) Math.toRadians(degrees);
        return new org.dyn4j.geometry.Vector2(Math.cos(radians), Math.sin(radians));

    }
    
    public static Packet messageToPacket(HotMessage.Packet o, Player p){
        if(!ServerMainTest.packets.containsKey(o.getId())) return null;

        return packetInstance(ServerMainTest.packets.get(o.getId()), p);

    }
    
    public static List<Object> fromString(List<Class> types, String s){
        List<Object> objects = new ArrayList<>();

        try {
            String[] ss = s.split("\\|");

            for(int i = 0; i < types.size(); i++){

                if (types.get(i) == String.class) objects.add(ss[i]);
                else if (types.get(i) == Integer.class){

                    try{

                        objects.add(Integer.parseInt(ss[i]));

                    }catch(Exception ignored){return null;}

                }else if (types.get(i) == Float.class){

                    try{

                        objects.add(Float.parseFloat(ss[i]));

                    }catch(Exception ignored){return null;}

                }else if (types.get(i) == Byte.class){

                    try{

                        objects.add(Byte.parseByte(ss[i]));

                    }catch(Exception ignored){return null;}

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return objects;

    }
    
    public static String toString(Object... objects){
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < objects.length; i++){
            Object o = objects[i];

            sb.append(o);

            if (i != objects.length - 1) sb.append("|");

        }

        return sb.toString();
    }
    
    public static String getType(GameObject go, ObjectType ot){

        switch(ot){
            case FISH:
                return ((Fish) go).getType().toString();
            default:
                return "";
        }

    }
    
    /** 
     * Check to see the type of the objects by compare the lengths and comparing the type of the objects to the input types
     * @param types types to compare 
     * @param objects objects to compare
     * @return false if size of type is different from object length, or object types and types are not the same
     */
    public static Boolean isItSuitable(List<Class> types, Object... objects){

        try {
            if (objects.length < types.size() || objects.length > types.size()) return false;

            
            //Check từng thông số đưa vào có cùng loại hay không, ví dụ FishType enum là FOX, và string "FOX" khá giống nhau nhưng mà khác loại, nên return flase
            for(int i = 0; i < types.size(); i++){

                if(objects[i].getClass() != types.get(i)) return false;

            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
    
    public static Boolean isItSuitable(List<Class> types, String s){

        try {
            String[] ss = s.split("\\|");

            if (ss.length < types.size() || ss.length > types.size()) return false;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
    
    /**
     * Send all other players a packet
     * @param c packet class to send
     * @param objects messages objects to send
     */
    public static void writeAll(Class<? extends Packet> c, Object... objects){

        for(Player p : ServerMainTest.players.values()){

            packetInstance(c, p).write(objects);

        }

    }
}
