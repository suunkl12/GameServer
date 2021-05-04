using System;
using System.Collections;
using System.Collections.Generic;
using System.Net;
using System.Net.Sockets;
using UnityEngine;

namespace CSharpSocket {
    public class Client : MonoBehaviour
    {
        // Start is called before the first frame update
        void Start()
        {
            Main(null);
        }

        // Update is called once per frame
        void Update()
        {

        }

        public static void Main(string[] args)
        {
            string toSend = "Hello!";

            IPEndPoint serverAddress = new IPEndPoint(IPAddress.Parse("192.168.100.34"), 6666);

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

            clientSocket.Close();
        }
    }
}


