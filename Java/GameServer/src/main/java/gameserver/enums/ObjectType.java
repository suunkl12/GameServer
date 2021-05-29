/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameserver.enums;

import gameserver.objects.*;

/**
 *
 * @author Khang
 */
public enum ObjectType {
    FISH, INDESTRUCTIBLE, ITEM, PLAYER, BULLET, NONE;

    public static ObjectType fromString(String s){

        try { return ObjectType.valueOf(s); }
        catch(Exception ignored) { return null; }

    }

    public static ObjectType fromObject(GameObject go){


        if (go instanceof Player) return ObjectType.PLAYER;
        
        if (go instanceof Bullet) return ObjectType.BULLET;
        
        if(go instanceof Fish) return ObjectType.FISH;
        
        return ObjectType.NONE;

    }

}
