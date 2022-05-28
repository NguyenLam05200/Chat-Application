/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controller.Client;
import java.awt.Image;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import javax.swing.ImageIcon;
import storage.Message;
import storage.User;

/**
 *
 * @author holohoi
 */
public class EachSearchMemberResult extends javax.swing.JPanel {

    javax.swing.JCheckBox jCheckBox1 = new javax.swing.JCheckBox();
    javax.swing.JLabel avata = new javax.swing.JLabel();
    javax.swing.JLabel name = new javax.swing.JLabel();
    javax.swing.JLabel lastseen = new javax.swing.JLabel();

    public EachSearchMemberResult() {
    }

    public EachSearchMemberResult(User _user) {
        jCheckBox1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        avata.setText("avatar");

        name.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        name.setForeground(new java.awt.Color(0, 0, 0));
        name.setText("Mr. b");

        lastseen.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        lastseen.setForeground(new java.awt.Color(102, 102, 102));
        lastseen.setText("last seen 16/03/2022");

        init(_user);
    }

    private void init(User _user) {

//        avatar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/user.png"))); // NOI18N
        avata.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/user.png")).getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH)));

        name.setText(_user.getName());

//===============
        if (isRightDate(_user.getLastSeen())) {
            String sendedAtDate = new SimpleDateFormat("hh:mm a").format(_user.getLastSeen());
            sendedAtDate = "last seen " + sendedAtDate;
            lastseen.setText("<html><p>" + sendedAtDate + "</p></html>");
        } else {
            String sendedAtDate = new SimpleDateFormat("dd/MM/yyyy").format(_user.getLastSeen());
            sendedAtDate = "last seen " + sendedAtDate;
            lastseen.setText("<html><p>" + sendedAtDate + "</p></html>");
        }
//================
        javax.swing.GroupLayout thisLayout = new javax.swing.GroupLayout(this);
        this.setLayout(thisLayout);
        thisLayout.setHorizontalGroup(
                thisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, thisLayout.createSequentialGroup()
                                .addComponent(avata, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(thisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(name, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lastseen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addComponent(jCheckBox1)
                                .addContainerGap())
        );
        thisLayout.setVerticalGroup(
                thisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(thisLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jCheckBox1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
                        .addComponent(avata, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(thisLayout.createSequentialGroup()
                                .addComponent(name, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lastseen, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }

    boolean isRightDate(Timestamp date2) {
        java.sql.Date date1 = new java.sql.Date(System.currentTimeMillis());
        return date1.getDay() == date2.getDay() && date1.getMonth() == date2.getMonth() && date1.getYear() == date2.getYear();
    }
}
