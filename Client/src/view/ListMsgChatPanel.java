package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.List;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.ParallelGroup;
import storage.Message;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author holohoi
 */
public class ListMsgChatPanel extends javax.swing.JPanel {

    // Variables declaration - do not modify
    List<EachMsgChat> listMsgChatPanel = new ArrayList<>();

    public ListMsgChatPanel(List<Message> _listMsgChat) {
        initComponents(_listMsgChat);
    }

    private void initComponents(List<Message> _listMsgChat) {
        setBackground(new java.awt.Color(116, 180, 224));
        setPreferredSize(new java.awt.Dimension(100, 100));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);

        int size = _listMsgChat.size();
        for (int i = 0; i < size; i++) {
            EachMsgChat eachMsgChat = new EachMsgChat(_listMsgChat.get(i));
            listMsgChatPanel.add(eachMsgChat);
        }

        GroupLayout.SequentialGroup verti = layout.createSequentialGroup();
        verti.addContainerGap(0, Short.MAX_VALUE);

        ParallelGroup horiLeft = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        ParallelGroup horiRight = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);

        for (int i = 0; i < size; i++) {
            EachMsgChat eachMsgChat = listMsgChatPanel.get(i);
            if (eachMsgChat.mine) {
                horiRight.addComponent(eachMsgChat, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
            } else {
                horiLeft.addComponent(eachMsgChat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
            }

            if (i == size - 1) {
                verti.addComponent(eachMsgChat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap();
            } else {
                verti.addComponent(eachMsgChat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
            }
        }

        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(verti)
        );

        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGap(0, 109, Short.MAX_VALUE)
                                                .addGroup(horiRight))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(horiLeft)
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
        );
    }// </editor-fold>

}
