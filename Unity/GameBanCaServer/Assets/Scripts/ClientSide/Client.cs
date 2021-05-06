using System;
using System.Collections;
using System.Collections.Generic;
using System.IO;
using System.Net;
using System.Net.Sockets;
using System.Text;
using UnityEngine;
using UnityEngine.Events;

namespace CSharpSocket {
    public class Client : MonoBehaviour
    {
        StreamWriter writer;
        NetworkStream stream;
        
        public Listener listener;


        // Start is called before the first frame update
        void Start()
        {

            listener.OnShoot.AddListener(SendData);

            print("Connection");
            TcpClient client = new TcpClient("127.0.0.1", 16000);
            stream = client.GetStream();
            stream.ReadTimeout = 10;
            //stream.WriteTimeout = 2;
            if (stream.CanRead)
            {
                writer = new StreamWriter(stream);
                print("Writer created");
                ReadData();
            }


        }

        public void SendData(float rotation)
        {
            writer.Write(rotation);
            writer.Flush();
        }

        // Update is called once per frame
        void Update()
        {
            ReadData();
        }
        public void ReadData()
        {
            if (stream.CanRead)
            {
                try
                {
                    
                    byte[] bLen = new Byte[4];
                    int data = stream.Read(bLen, 0, 4);
                    if (data > 0)
                    {
                        int len = BitConverter.ToInt32(bLen, 0);
                        print("len = " + len);
                        Byte[] buff = new byte[1024];
                        try
                        {
                            data = stream.Read(buff, 0, len);
                            if (data > 0)
                            {
                                string result = Encoding.ASCII.GetString(buff, 0, data);
                                stream.Flush();
                                ParseData(result);
                            }
                        }
                        catch(Exception ex)
                        {
                            Debug.LogError(ex.Message);
                        }
                    }
                }
                catch (Exception ex)
                {
                }
            }
        }

        public void ParseData(string result)
        {
            float rotation = float.Parse(result);
            listener.OnReceiveRotationOfOtherGunRotateGun(rotation);
            print(result);
        }

        public static void Main(string[] args)
        {
            #region ClientTest
            /*
            string toSend = "Hello!";

            IPEndPoint serverAddress = new IPEndPoint(IPAddress.Parse("192.168.100.34"), 4343);

            Socket clientSocket = new Socket(AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.Tcp);
            clientSocket.Connect(serverAddress);
            
            // Sending
            int toSendLen = System.Text.Encoding.ASCII.GetByteCount(toSend);
            byte[] toSendBytes = System.Text.Encoding.ASCII.GetBytes(toSend);
            byte[] toSendLenBytes = System.BitConverter.GetBytes(toSendLen);
            clientSocket.Send(toSendLenBytes);
            clientSocket.Send(toSendBytes);
            // Receiving
            byte[] rcvLenBytes = new byte[4];
            clientSocket.Receive(rcvLenBytes);
            int rcvLen = System.BitConverter.ToInt32(rcvLenBytes, 0);
            byte[] rcvBytes = new byte[rcvLen];
            clientSocket.Receive(rcvBytes);
            string rcv = System.Text.Encoding.ASCII.GetString(rcvBytes);

            Debug.Log("Client received: " + rcv);

            clientSocket.Close();*/
            #endregion
        }
    }
}


