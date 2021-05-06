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
                // server gửi về tên animal, target( vector3 x,y,z), speed float , position x,y
                rnd = Random.Range(0,AnimalUnit.Count);
                var animal = Instantiate(AnimalUnit[rnd], t.position, Quaternion.identity);
                //TODO Đẩy con vật về target 
                //MoveToTarget(animal,, Random.Range(2f,4f);
                
            }
            StartCoroutine( WaitFor() );
        }
        //yield on a new YieldInstruction that waits for 5 seconds.
            
    }

    public void MoveToTarget(Transform animal, Vector2 target, float speed)
    {
        while (animal.position != (Vector3)target)
        {
            animal.position = Vector2.MoveTowards(animal.position, target, speed * Time.deltaTime);
            if (animal.position == (Vector3)target)
            {
                Destroy(gameObject);
                return;
            }
        }
    }
}
