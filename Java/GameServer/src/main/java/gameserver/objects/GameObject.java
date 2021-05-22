/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameserver.objects;

import gameserver.utils.Rotation;
import gameserver.utils.Vector2;

/**
 *
 * @author Khang
 */
public abstract class GameObject {

    private Integer id;
    private Vector2 position;
    private Rotation rotation;

    public GameObject(Integer id, Vector2 position, Rotation rotation) {
        this.id = id;
        this.position = position;
        this.rotation = rotation;
    }
    
    
    public GameObject(Integer id, Vector2 position){

        this.id = id;
        this.position = position;
        this.rotation = new Rotation();
    }

    public Integer getId(){
        return id;
    }

    public Vector2 getPosition() {
        return position;
    }
    public void setPosition(Vector2 position) {
        this.position = position;
    }
    
    public Rotation getRotation() {
        return rotation;
    }

    public void setRotation(Rotation rotation) {
        this.rotation = rotation;
    }

}