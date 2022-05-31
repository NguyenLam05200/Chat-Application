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
public class handleResponseAuth {

    public static void register(Object[] res) {
        String status = res[1].toString();
        String msg = res[2].toString();
        if (status.equalsIgnoreCase("Error")) {
            auth.showError(msg);
        } else if (status.equalsIgnoreCase("Ok")) {
            auth.registerSuccess(msg);
        }
    }

    public static void login(Object[] res) {
        String status = res[1].toString();
        String msg = res[2].toString();
        if (status.equalsIgnoreCase("Error")) {
            auth.showError(msg);
        } else if (status.equalsIgnoreCase("Ok")) {
            user = new User(res);

            auth.loginSuccess(msg);
        }
    }

    public static void initDashboard(Object[][] res) {
        int size = (res.length - 1) / 2; // 5 - 1 = 4/2 = 2

        listMsgContacts = new ArrayList<>();
        listContacts = new ArrayList<>();
        listContactsID = new ArrayList<>();

        for (int i = 1; i <= size; i++) {

            Object[] eachMsg = res[i];
            Object[] eachContact = res[i + size];
            listContactsID.add(eachContact[0].toString());
            listMsgContacts.add(eachMsg);
            listContacts.add(eachContact);
        }

    }
}
