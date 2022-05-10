/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import dao.MessageDAO;
import static dao.MessageDAO.getListMsgContact;
import dao.UserDAO;
import entity.Message;
import entity.User;
import java.security.NoSuchAlgorithmException;
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

        Pair pair = getListMsgContact(user);
        List<Message> listMsg = (List<Message>) pair.getKey();
        List<User> listContacts = (List<User>) pair.getValue();

        int size = listMsg.size();
        res = new Object[size * 2 + 1][];
        res[0] = new Object[]{_dispatchMsg};
        for (int i = 1; i < size + 1; i++) {
            res[i] = listMsg.get(i - 1).getObject();
            res[i + size] = listContacts.get(i - 1).getObject();
        }
        return res;
    }

}
