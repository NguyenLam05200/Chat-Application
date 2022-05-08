/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import dao.UserDAO;
import entity.User;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.handlePassword;

/**
 *
 * @author holohoi
 */
public class HandleRequestAuth {

    public static Object[] register(Object[] req, String _dispatchMsg) {
        // init response:
        Object[] res;
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
        res = new Object[]{dispatchMsg, status, msg};
        return res;
    }

    public static Object[] login(Object[] req, String _dispatchMsg) {
        // init response:
        Object[] res;
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
                status = "Ok";
                msg = "Login Successfully!";
                res = new Object[]{
                    dispatchMsg,
                    status,
                    msg,
                    user.getId(),
                    user.getName()
                };
                return res;
            } else {
                msg = "Password is not correct, please try again!";
            }
        }
        res = new Object[]{dispatchMsg, status, msg};
        return res;
    }
}
