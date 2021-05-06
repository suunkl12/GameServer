using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class AnimalManager : MonoBehaviour
{
    // Start is called before the first frame update
    [SerializeField]
    private List<GameObject> AnimalUnit;
    [SerializeField]
    private List<Transform> nodes;
    private int rnd;
    private bool wt= false;

    void Start()
    {
        
        StartCoroutine(WaitFor());
    }
    IEnumerator WaitFor()
    {
        wt = true;
        // process pre-yield
        yield return new WaitForSeconds( 5.0f );
        // process post-yield
        wt = false;
    }
    // Update is called once per frame
    
    void Update()
    {
        if(!wt )
        {
            foreach (Transform t in nodes)
            {
                rnd = Random.Range(0,AnimalUnit.Count);
                Instantiate(AnimalUnit[rnd], t.position, Quaternion.identity);
                
            }
            StartCoroutine( WaitFor() );
        }
        //yield on a new YieldInstruction that waits for 5 seconds.
            
    }
}
