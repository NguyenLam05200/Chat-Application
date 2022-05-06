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
        ObjectOutputStream dos = new ObjectOutputStream(s.getOutputStream());
        dos.flush();
        ObjectInputStream dis = new ObjectInputStream(s.getInputStream());

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
                        Object[] msg = (Object[]) dis.readObject();
                        for (Object x : msg) {
                            System.out.println(x);
                        }
//                        System.out.println(msg);
                    } catch (IOException e) {
                        System.out.println("break in readMsg");
                        System.out.println("Server disconnected!");
                        break;
//                        e.printStackTrace();
                    } catch (ClassNotFoundException ex) {
                        System.out.println("catch here");
                        Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });

//        sendMessage.start();
        readMessage.start();

    }
}
