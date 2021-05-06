using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class Game_Manager : MonoBehaviour
{
    public GameObject GunContainer;
    public Gun[] slotGun;
    public Gun playerIn;
    // Start is called before the first frame update
    void Start()
    {
        slotGun = new Gun[GunContainer.gameObject.transform.childCount];
        for (int i = 0; i < GunContainer.gameObject.transform.childCount; i++)
        {
            slotGun[i] = GunContainer.gameObject.transform.GetChild(i).gameObject.GetComponent<Gun>();
        }
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
                //Debug.Log("You are in " + slotGun[i]);
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
                //Debug.Log("You are in " + slotGun[i]);
                break;
            }
            
        } while (true);
         
    }
}
