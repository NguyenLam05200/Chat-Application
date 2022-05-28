/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import static controller.Client.*;
import static controller.Client.user;
import static controller.Client.dashboard;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import storage.Message;

import storage.User;
import view.Dashboard;

/**
 *
 * @author holohoi
 */
public class handleResponseOther {

    public static void getListMsgChat(Object[][] res) {
        int size = res.length; // 5 - 1 = 4/2 = 2
        curListMsgChat = new ArrayList<>();

        for (int i = 1; i < size; i++) {
            Object[] eachMsg = res[i];
            Message msg;
            int sendBy = Integer.parseInt(eachMsg[3].toString());
            if (sendBy == user.getId()) {
                msg = new Message(eachMsg, user, curContact);
            } else {
                msg = new Message(eachMsg, curContact, user);
            }
            curListMsgChat.add(msg);
        }

    }

    public static List<User> getResultSearchUser(Object[][] res) {
        int size = res.length; // 5 - 1 = 4/2 = 2
        List<User> listUserResult = new ArrayList<>();

        for (int i = 1; i < size; i++) {
            Object[] eachResult = res[i];

            listUserResult.add(new User(eachResult, true));
        }
        return listUserResult;
    }
}
