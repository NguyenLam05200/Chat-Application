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
import Config.MsgDispatch;
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import storage.User;
import view.Auth;
import view.Dashboard;

public class Client {

    static User user;
    static Auth auth;
    static Dashboard dashboard;

    public static void main(String args[]) throws UnknownHostException, IOException {

        // establish the connection
        Socket s = new Socket(Env.IPAddress, Env.Port);

        // obtaining input and out streams
        ObjectOutputStream dos = new ObjectOutputStream(s.getOutputStream());
        dos.flush();
        ObjectInputStream dis = new ObjectInputStream(s.getInputStream());

        auth = new Auth(dos);
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new Dashboard(s, dos).setVisible(true);
                auth.setVisible(true);
//                new Auth(dos).setVisible(true);
            }
        });

        // readMessage thread
        Thread readMessage = new Thread(new Runnable() {
            @Override
            public void run() {
                Object[] res;
                while (true) {
                    try {
                        // read the message sent to this client
                        res = (Object[]) dis.readObject();
                        String dispatchMsg = res[0].toString();
                        switch (dispatchMsg) {
                            case MsgDispatch.LOGIN:
                                handleResponseAuth.login(res);
                                break;
                            case MsgDispatch.REGISTER:
                                handleResponseAuth.register(res);
                                break;
                            default:
                                throw new AssertionError();
                        }
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
