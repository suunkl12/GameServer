/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameserver.utils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Khang
 */
public class Utils {
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
}
