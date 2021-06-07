/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameserver.enums;
import java.awt.*;

/**
 *
 * @author Khang
 */
public enum FishType {
    FOX(1,Color.ORANGE),
    RABBIT(2,Color.WHITE),
    PIG(3,Color.PINK),
    CHICKEN(4,Color.YELLOW),
    MOUSE(5,Color.BLACK);
    
    private Integer health;
    private Color isColor;
    FishType(Integer health, Color isColor){
        this.health = health;
        this.isColor = isColor;
    }

    public Integer getHealth(){
        return health;
    }
    public Color getColor(){
        return isColor;
    }
    public static FishType fromString(String s){

        try { return FishType.valueOf(s); }
        catch(Exception ignored) { return null; }

    }

}
