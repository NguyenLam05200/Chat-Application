/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import dao.GroupDAO;
import dao.MessageDAO;
import dao.MessageGroupDAO;
import dao.UserDAO;
import entity.Group;
import entity.Message;
import entity.Message_Group;
import entity.User;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Pair;
import util.ObjectHanlde;
import util.handlePassword;

/**
 *
 * @author holohoi
 */
public class HandleRequestAuth {

    public static Object[][] register(Object[] req, String _dispatchMsg) {
        // init response:
        Object[][] res = new Object[1][];
        String dispatchMsg = _dispatchMsg;
        String status = "Error";
        String msg;

        // handle request:
        String username = req[1].toString();
        String password = req[2].toString();
        String name = req[3].toString();

        User user = UserDAO.findOneByUsername(username);
        if (user != null) {
            msg = "User is already exist, please try another username!";
        } else {
            try {
                password = handlePassword.hashPassword(password);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(HandleRequestAuth.class.getName()).log(Level.SEVERE, null, ex);
            }
            User newUser = new User(username, password, name);
            boolean addSuccess = UserDAO.addUser(newUser);
            if (addSuccess) {
                status = "Ok";
                msg = "Register successfully!";
            } else {
                msg = "Regiter fail!";
            }
        }
        res[0] = new Object[]{dispatchMsg, status, msg};
        return res;
    }

    public static Object[][] login(Object[] req, String _dispatchMsg, ClientHandler clientHandler) {
        // init response:
        Object[][] res = new Object[1][];
        String dispatchMsg = _dispatchMsg;
        String status = "Error";
        String msg;

        // handle request:
        String username = req[1].toString();
        String password = req[2].toString();

        User user = UserDAO.findOneByUsername(username);
        if (user == null) {
            msg = "Username is not exist!";
        } else {
            try {
                password = handlePassword.hashPassword(password);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(HandleRequestAuth.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (user.getPassword().equals(password)) {
                clientHandler.setUser(user);

                status = "Ok";
                msg = "Login Successfully!";
                Object[] res1 = new Object[]{dispatchMsg, status, msg};
                Object[] res2 = user.getObject();
                res[0] = ObjectHanlde.concatObject(res1, res2);
                return res;
            } else {
                msg = "Password is not correct, please try again!";
            }
        }
        res[0] = new Object[]{dispatchMsg, status, msg};
        return res;
    }

    public static Object[][] initDasboard(User user, String _dispatchMsg) {
        Object[][] res;
        Pair pair = MessageDAO.getListMsgContact(user);
        List<Message> listMsgSingles = (List<Message>) pair.getKey();
        List<User> listUsers = (List<User>) pair.getValue();

        int size1 = listUsers.size();
        List<Group> listGroups = GroupDAO.getListGroup(user);
        List<Message_Group> listMsgGroups = MessageGroupDAO.getListMsgContact(listGroups);
        int size2 = listGroups.size();

        int size = size1 + size2;
        List<Object[]> temp_contact = new ArrayList<>();
        List<Object[]> temp_msg = new ArrayList<>();

        for (int i = 0; i < size1; i++) {
            temp_contact.add(listUsers.get(i).getObject());
            temp_msg.add(listMsgSingles.get(i).getObject(user));
        }
        for (int i = size1; i < size; i++) {
            temp_contact.add(listGroups.get(i - size1).getObject());
            Message_Group x = listMsgGroups.get(i - size1);
            temp_msg.add(x == null ? new Object[]{} : x.getObject(user));
        }

        List<Object[]> listContacts = new ArrayList<>();
        List<Object[]> listMsg = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            Object[] curContact = temp_contact.get(i);
            Object[] curMsg = temp_msg.get(i);

            int curSize = listContacts.size();
            switch (curSize) {
                case 0:
                    listContacts.add(curContact);
                    listMsg.add(curMsg);
                    break;
                case 1:
                    int compare = compareObjectTime(listContacts.get(0), curContact, listMsg.get(0), curMsg);
                    if (compare >= 0) {
                        listContacts.add(curContact);
                        listMsg.add(curMsg);
                    } else {
                        listContacts.add(0, curContact);
                        listMsg.add(0, curMsg);
                    }
                    break;
                default:
                    for (int j = 0; j < curSize; j++) {
                        if (j == 0) {
                            int compare_first = compareObjectTime(listContacts.get(0), curContact, listMsg.get(0), curMsg);
                            if (compare_first < 0) {
                                listContacts.add(0, curContact);
                                listMsg.add(0, curMsg);
                                break;
                            }
                        }

                        if (j == curSize - 1) {
                            listContacts.add(curContact);
                            listMsg.add(curMsg);
                            break;
                        }

                        int compare1 = compareObjectTime(listContacts.get(j), curContact, listMsg.get(j), curMsg);
                        int compare2 = compareObjectTime(listContacts.get(j + 1), curContact, listMsg.get(j + 1), curMsg);
                        if (compare1 >= 0 && compare2 >= 0) {
                            listContacts.add(j + 1, curContact);
                            listMsg.add(j + 1, curMsg);
                            break;
                        }
                    }
                    break;
            }
        }

        res = new Object[size * 2 + 1][];
        res[0] = new Object[]{_dispatchMsg};
        for (int i = 0; i < size; i++) {
            res[i + 1] = listMsg.get(i);
            res[i + 1 + size] = listContacts.get(i);
        }
        return res;
    }

    static int compareObjectTime(Object[] contact1, Object[] contact2, Object[] msg1, Object[] msg2) {
// >0: msg1 sendTime value greater
// <0: msg2 sendTime value greater

        Timestamp ts1;
        Timestamp ts2;

        int len1 = msg1.length;
        if (len1 == 0 || msg1 == null) {
            ts1 = Timestamp.valueOf(contact1[2].toString());
        } else {
            ts1 = Timestamp.valueOf(msg1[2].toString());
        }

        int len2 = msg2.length;
        if (len2 == 0 || msg2 == null) {
            ts2 = Timestamp.valueOf(contact2[2].toString());
        } else {
            ts2 = Timestamp.valueOf(msg2[2].toString());
        }

        return ts1.compareTo(ts2);
    }

}
