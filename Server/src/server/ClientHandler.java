/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import Config.MsgDispatch;
import entity.User;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author holohoi
 */
// ClientHandler class
public class ClientHandler implements Runnable {

    User user;
    final ObjectInputStream dis;
    final ObjectOutputStream dos;
    Socket s;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // constructor
    public ClientHandler(Socket s, ObjectInputStream dis, ObjectOutputStream dos) {
        this.dis = dis;
        this.dos = dos;
        this.s = s;
    }

    @Override
    public void run() {

        Object[] received;
        Object[][] res;
        while (true) {
            try {
                // receive the string
                System.out.println("==================");
                received = (Object[]) dis.readObject();

                System.out.print((user == null ? "null" : user.getName()) + ": ");
                String dispatchMsg = received[0].toString();
                System.out.println(dispatchMsg);

                switch (dispatchMsg) {
                    case MsgDispatch.LOGIN:
                        res = HandleRequestAuth.login(received, dispatchMsg, this);
                        dos.writeObject(res);
                        break;
                    case MsgDispatch.REGISTER:
                        res = HandleRequestAuth.register(received, dispatchMsg);
                        dos.writeObject(res);
                        break;
                    case MsgDispatch.INIT_DASHBOARD:
                        res = HandleRequestAuth.initDasboard(user, dispatchMsg);
                        dos.writeObject(res);
                        break;
                    default:
                        throw new AssertionError();
                }

            } catch (IOException e) {
                System.out.println((user == null ? "null" : user.getName()) + " disconnected!");
                break;
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            // closing resources
            this.dis.close();
            this.dos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
