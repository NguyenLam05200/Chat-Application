/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import static controller.Client.dashboard;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.swing.GroupLayout;
import storage.Message;
import storage.User;

/**
 *
 * @author holohoi
 */
public class ListMsgContactPanel extends javax.swing.JPanel {

    public static List<EachMsgContact> listMsgContactPanel;
    public static List<User> listContacts;
    int size;
    public static int curClick;

    public static void setCurClick(int id) {
        dashboard.clickContactMsg(listContacts.get(id));
        curClick = id;
    }

    public ListMsgContactPanel() {
    }

    public ListMsgContactPanel(List<User> listContacts, List<Message> listMsgContacts, List<Integer> contacts) {
        curClick = -1;
        this.size = contacts.size();
        this.listContacts = listContacts;
        listMsgContactPanel = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            listMsgContactPanel.add(new EachMsgContact(listMsgContacts.get(i), listContacts.get(i), i));
        }
        myInit();
    }

    void myInit() {

        this.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout thisLayout = new javax.swing.GroupLayout(this);
        this.setLayout(thisLayout);

        GroupLayout.ParallelGroup hori = thisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        GroupLayout.SequentialGroup verti = thisLayout.createSequentialGroup();

        for (int i = 0; i < size; i++) {
            hori.addComponent(listMsgContactPanel.get(i), javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
            if (i == size - 1) {
                verti.addComponent(listMsgContactPanel.get(i), javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
            } else {
                verti.addComponent(listMsgContactPanel.get(i), javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
            }
        }

        thisLayout.setHorizontalGroup(hori);
        thisLayout.setVerticalGroup(
                thisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(verti)
        );
    }

    public static void handleClickEachMsgClick(int id) {

    }
}
