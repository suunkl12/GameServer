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
    //enum can have values inside it
    FOX(2,Color.ORANGE),
    RABBIT(2,Color.WHITE),
    PIG(3,Color.PINK),
    CHICKEN(2,Color.YELLOW),
    MOUSE(1,Color.BLACK),
    NONE(0,Color.WHITE);
    
    private Integer animalID;
    private Color isColor;
    
    FishType(Integer health, Color isColor){
        this.animalID = health;
        this.isColor = isColor;
    }

    public Integer getAnimalID(){
        return animalID;
    }
    public Color getColor(){
        return isColor;
    }
    public static FishType fromString(String s){

        try { return FishType.valueOf(s); }
        catch(Exception ignored) { return null; }

    }

}
