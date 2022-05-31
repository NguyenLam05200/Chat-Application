/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import static controller.Client.*;
import static controller.Client.user;
import static controller.Client.dashboard;
import java.util.ArrayList;
import java.util.Arrays;
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
            curListMsgChat.add(eachMsg);
        }

    }

    public static List<Object[]> getResultSearchUser(Object[][] res) {
        int size = res.length; // 5 - 1 = 4/2 = 2
        List<Object[]> listUserResult = new ArrayList<>();

        for (int i = 1; i < size; i++) {
            Object[] eachResult = res[i];

            listUserResult.add(eachResult);
        }
        return listUserResult;
    }
}
