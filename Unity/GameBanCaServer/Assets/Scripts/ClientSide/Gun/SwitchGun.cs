using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class SwitchGun : MonoBehaviour
{
    // Start is called before the first frame update
    public Gun[] slotGun;
    public Game_Manager game_Manager;
    void Start()
    {
        slotGun = game_Manager.slotGun;
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    public void switchGun(int i)
    {
        if(slotGun[i].inUsed == false)
        {
            game_Manager.playerIn.inUsed = false;
            slotGun[i].inUsed = true;
            game_Manager.playerIn = slotGun[i];
            Debug.Log(slotGun[i]);

        }
    }    
}
