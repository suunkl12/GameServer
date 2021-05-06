using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class Game_Manager : MonoBehaviour
{
    public Gun[] slotGun;
    public Gun playerIn;
    // Start is called before the first frame update
    void Start()
    {
        setRandom();
        //setSlot();
    }

    // Update is called once per frame
    void Update()
    {
        
    }
    void setSlot()
    {
        for (int i = 0; i < slotGun.Length; i++)
        {
            if (!slotGun[i].inUsed)
            {
                slotGun[i].inUsed = true;
                playerIn = slotGun[i];
                Debug.Log(slotGun[i]);
                break;
            }
            else
                continue;
        }
    }
    void setRandom()
    {
        int i;
        do
        {
            i = Random.Range(0, slotGun.Length - 1);
            if (!slotGun[i].inUsed)
            {
                slotGun[i].inUsed = true;
                playerIn = slotGun[i];
                Debug.Log(slotGun[i]);
                break;
            }
            
        } while (true);
         
    }
}
