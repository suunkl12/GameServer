using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.Events;

[CreateAssetMenu( fileName ="Listerner",menuName ="ScriptableObject/Listener")]
public class Listener : ScriptableObject
{

    public UnityEvent<float> OnShoot = new MyCustomUnityEvent();


    public void OnShootSendData(float rotation2d)
    {
        OnShoot?.Invoke(rotation2d);
    }

    public UnityEvent<float> OnReceiveRotationOfOtherGun = new MyCustomUnityEvent();


    public void OnReceiveRotationOfOtherGunRotateGun(float rotation2d)
    {
        OnReceiveRotationOfOtherGun?.Invoke(rotation2d);
    }
}

public class MyCustomUnityEvent : UnityEvent<float>
{}