using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Ammo : MonoBehaviour, ITakeDamage
{
    // Start is called before the first frame update

    public Rigidbody2D rb;
    float speedRotate = 500f;
    public bool rotate;
    private void Start()
    {
        rb = GetComponent<Rigidbody2D>();
    }
    private void Update()
    {
        if(rotate)
            transform.Rotate(Vector3.forward * speedRotate * Time.deltaTime);
        
    }
    void OnBecameInvisible()
    {
        Destroy(gameObject);
    }

    public void TakeDamage(int damage)
    {
        throw new System.NotImplementedException();
    }

    private void OnTriggerEnter2D(Collider2D other)
    {
        if(other.gameObject.CompareTag("Animal"))
        {
            Animator ani = other.GetComponent<Animator>();
            ani.SetBool("Dead", true);
            other.GetComponent<CircleCollider2D>().enabled = false;
            Destroy(this.gameObject);
        }    
    }
}
