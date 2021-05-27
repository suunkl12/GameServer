/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package egroup.gameserver;

import java.util.List;
import java.util.Random;

/**
 *
 * @author Khang
 */
public class GunAssigmnent {
    public static int[] roomList;
    
    public static Boolean[] gunList = new Boolean[4];
    
    public static int assignGun(int max){
         do{
             int gunIndex = (int) (max*Math.random());
             if(!gunList[gunIndex]){
                gunList[gunIndex] = true;
                return gunIndex;
             }
         }while(true);
    }
}
