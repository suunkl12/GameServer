/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameserver.managers;

import gameserver.physics.BanCaPhysics;

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
}
