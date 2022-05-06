/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author holohoi
 */
import Config.Env;
import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client1 {

    public static void main(String args[]) throws UnknownHostException, IOException {
        Scanner scn = new Scanner(System.in);

        // establish the connection
        Socket s = new Socket(Env.IPAddress, Env.Port);

        // obtaining input and out streams
        DataInputStream dis = new DataInputStream(s.getInputStream());
        DataOutputStream dos = new DataOutputStream(s.getOutputStream());

//test
        testSendMsg testSend = new testSendMsg(dos);
        testSend.start();

        // sendMessage thread
        Thread sendMessage = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {

                    // read the message to deliver.
                    String msg = scn.nextLine();

                    try {
                        // write on the output stream
                        dos.writeUTF(msg);
                    } catch (IOException e) {
                        System.out.println("Server disconnected!");
                        break;
//                        e.printStackTrace();
                    }
                }
            }
        });

        // readMessage thread
        Thread readMessage = new Thread(new Runnable() {
            @Override
            public void run() {

                while (true) {
                    try {
                        // read the message sent to this client
                        String msg = dis.readUTF();
                        System.out.println(msg);
                    } catch (IOException e) {
                        System.out.println("break in readMsg");
                        System.out.println("Server disconnected!");
                        break;
//                        e.printStackTrace();
                    }
                }
            }
        });

//        sendMessage.start();
        readMessage.start();

    }
}

class testSendMsg extends Thread {

    DataOutputStream dos;

    testSendMsg(DataOutputStream _dos) {
        dos = _dos;
    }

    public void run() {
        while (true) {

            // read the message to deliver.
            String msg = "hello client 0#client 0";

            try {
                // write on the output stream
                dos.writeUTF(msg);
                Thread.sleep(1000);

            } catch (IOException e) {
                System.out.println("break in testSendMsg");
                System.out.println("Server disconnected!");
                break;
//                        e.printStackTrace();
            } catch (InterruptedException ex) {
                Logger.getLogger(testSendMsg.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
