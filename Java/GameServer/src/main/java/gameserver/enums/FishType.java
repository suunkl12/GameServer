/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameserver.enums;

/**
 *
 * @author Khang
 */
public enum FishType {
    FOX(1), RABBIT(2), PIG(3), CHICKEN(4), MOUSE(5);
    
    private Integer health;

    FishType(Integer health){

        this.health = health;

    }

    public Integer getHealth(){
        return health;
    }

    public static FishType fromString(String s){

        try { return FishType.valueOf(s); }
        catch(Exception ignored) { return null; }

    }
}
