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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import storage.Message;
import storage.User;
import view.Auth;
import view.Dashboard;

public class Client {

    public static User user;
    public static Auth auth;
    public static Dashboard dashboard;
    public static List<Object[]> listMsgContacts;
    public static List<Object[]> listContacts;
    public static List<String> listContactsID;

    public static Object[] curContact;
    public static List<Object[]> curListMsgChat;

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
                Object[][] res;
                while (true) {
                    try {
                        // read the message sent to this client
                        res = (Object[][]) dis.readObject();
                        String dispatchMsg = res[0][0].toString();
                        switch (dispatchMsg) {
                            case MsgDispatch.LOGIN:
                                handleResponseAuth.login(res[0]);
                                break;
                            case MsgDispatch.REGISTER:
                                handleResponseAuth.register(res[0]);
                                break;
                            case MsgDispatch.INIT_DASHBOARD:
                                handleResponseAuth.initDashboard(res);
                                auth.initDashboardSuccess();

                                dashboard = new Dashboard(dos);

                                java.awt.EventQueue.invokeLater(new Runnable() {
                                    public void run() {
                                        dashboard.setVisible(true);
                                    }
                                });
                                break;
                            case MsgDispatch.GET_LIST_MSG_CHAT:
                                handleResponseOther.getListMsgChat(res);
                                dashboard.renderListMsgChat();
                                break;
                            case MsgDispatch.RECEIVED_MSG:
                                Object[] sendBy = res[2];
                                Object[] newMsg = res[1];

                                dashboard.getNewMsg(newMsg, sendBy);
                                break;
                            case MsgDispatch.SEARCH_USER:
                                List<Object[]> listUserResult = handleResponseOther.getResultSearchUser(res);
                                dashboard.renderSearchUserResults(listUserResult, "forContact");
                                break;
                            case MsgDispatch.SEARCH_USER_FOR_ADD:
                                List<Object[]> listUserResultForAdd = handleResponseOther.getResultSearchUser(res);
                                dashboard.renderSearchUserResults(listUserResultForAdd, "forAdd");
                                break;
                            case MsgDispatch.CREATE_NEW_GROUP:
                                Object[] newGr = res[1];
                                dashboard.createNewGroupSuccess(newGr);
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
