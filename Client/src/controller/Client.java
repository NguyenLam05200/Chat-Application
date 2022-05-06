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
import view.Dashboard;

public class Client {

    public static void main(String args[]) throws UnknownHostException, IOException {
        Scanner scn = new Scanner(System.in);

        // establish the connection
        Socket s = new Socket(Env.IPAddress, Env.Port);

        // obtaining input and out streams
        DataInputStream dis = new DataInputStream(s.getInputStream());
        DataOutputStream dos = new DataOutputStream(s.getOutputStream());

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Dashboard(s, dos).setVisible(true);
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
