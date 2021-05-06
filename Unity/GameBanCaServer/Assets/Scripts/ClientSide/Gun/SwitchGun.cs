using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
public class SwitchGun : MonoBehaviour
{
    // Start is called before the first frame update
    public Gun[] slotGun;
    public Button[] btn;
    public Game_Manager game_Manager;
    void Start()
    {
        slotGun = game_Manager.slotGun;
        btn = new Button[transform.childCount];
        for (int i = 0; i < transform.childCount; i++)
        {
            btn[i] = transform.GetChild(i).gameObject.GetComponent<Button>();
        }
    }

    // Update is called once per frame
    void Update()
    {

        for (int i = 0; i < slotGun.Length; i++)
        {
            if (slotGun[i].inUsed)
            {
                btn[i].interactable = false;
            }
            else
                btn[i].interactable = true;
        }
    }

    public void switchGun(int i)
    {
        if(slotGun[i].inUsed == false)
        {
            game_Manager.playerIn.inUsed = false;
            slotGun[i].inUsed = true;
            game_Manager.playerIn = slotGun[i];
            //Debug.Log("Switching to " + slotGun[i]);
        }
    }    
}
