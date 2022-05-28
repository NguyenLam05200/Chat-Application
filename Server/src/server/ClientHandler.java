/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import Config.MsgDispatch;
import entity.Message;
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

    User user = null;
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
                    case MsgDispatch.GET_LIST_MSG_CHAT:
                        res = HandleRequestOther.getListMsgChat(received, dispatchMsg, user);
                        dos.writeObject(res);
                        break;
                    case MsgDispatch.DELIVER_MSG:
                        Message msg = HandleRequestOther.sendMsg(received, dispatchMsg, user);
                        Server.deliverMsg(msg, user);
                        break;
                    case MsgDispatch.SEARCH_USER:
                        res = HandleRequestOther.searchUser(received, dispatchMsg);
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

    public void getMsg(Message msg, User sendBy) {
        Object[][] res;
        res = new Object[3][];
        res[0] = new Object[]{MsgDispatch.RECEIVED_MSG};
        res[1] = msg.getObject();
        res[2] = sendBy.getObject();
        try {
            dos.writeObject(res);
        } catch (Exception e) {
        }
    }
}
