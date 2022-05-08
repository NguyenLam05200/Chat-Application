/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import static controller.Client.auth;

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
            int userID = Integer.parseInt(res[3].toString());
            String name = res[4].toString();
            System.out.println("id: " + userID + "; name: " + name);
//            auth.registerSuccess(msg);
        }
    }
}
