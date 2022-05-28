/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.util.ArrayList;
import java.util.List;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import storage.User;

/**
 *
 * @author holohoi
 */
public class ListSearchMemberResult extends javax.swing.JPanel {

    public ListSearchMemberResult(List<User> _listMemberResults) {
        initComponents(_listMemberResults);
    }

    private void initComponents(List<User> _listMemberResults) {
        this.setBackground(new java.awt.Color(255, 255, 255));

        List<EachSearchMemberResult> listMemberResultPanel = new ArrayList<>();
        for (User _user : _listMemberResults) {
            listMemberResultPanel.add(new EachSearchMemberResult(_user));
        }

        javax.swing.GroupLayout thisLayout = new javax.swing.GroupLayout(this);
        this.setLayout(thisLayout);

        ParallelGroup hori = thisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        SequentialGroup verti = thisLayout.createSequentialGroup();
        verti.addContainerGap();

        for (EachSearchMemberResult eachResult : listMemberResultPanel) {
            hori.addComponent(eachResult, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);

            verti.addComponent(eachResult, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        }
        thisLayout.setHorizontalGroup(
                thisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(thisLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(hori)
                                .addContainerGap())
        );
        thisLayout.setVerticalGroup(
                thisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(verti)
        );
    }
}
