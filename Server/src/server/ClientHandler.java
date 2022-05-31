/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import Config.MsgDispatch;
import dao.UserDAO;
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
                        HandleRequestOther.sendMsg(received, dispatchMsg, user);
                        break;
                    case MsgDispatch.SEARCH_USER:
                        res = HandleRequestOther.searchContact(received, dispatchMsg, user);
                        dos.writeObject(res);
                        break;
                    case MsgDispatch.SEARCH_USER_FOR_ADD:
                        res = HandleRequestOther.searchUser(received, dispatchMsg);
                        dos.writeObject(res);
                        break;
                    case MsgDispatch.CREATE_NEW_GROUP:
                        res = HandleRequestOther.createNewGroup(received, dispatchMsg, user);
                        dos.writeObject(res);
                        break;
                    case MsgDispatch.LOGOUT:
                        if (user != null) {
                            UserDAO.updateLastSeen(user);
                        }
                        this.user = null;
                        break;
                    default:
                        throw new AssertionError();
                }

            } catch (IOException e) {
                System.out.println((user == null ? "null" : user.getName()) + " disconnected!");
                // set last seen of user is time leaving
                if (user != null) {
                    UserDAO.updateLastSeen(user);
                }
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

    public void getMsg(boolean isUser, Object[] msg, Object[] from) {
        Object[][] res;
        res = new Object[3][];
        res[0] = new Object[]{MsgDispatch.RECEIVED_MSG, isUser};
        res[1] = msg;
        res[2] = from;
        try {
            dos.writeObject(res);
        } catch (Exception e) {
        }
    }
}
