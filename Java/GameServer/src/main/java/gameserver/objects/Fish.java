/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameserver.objects;

import gameserver.enums.FishType;
import gameserver.utils.Rotation;
import gameserver.utils.Vector2;

/**
 *
 * @author Khang
 */
public class Fish extends GameObject{
    int health;
    FishType ft;
    
    public Fish(Integer id, Vector2 position, Rotation rotation,FishType ft) {
        super(id, position, rotation);
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
    
    
}
