/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.Image;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import javax.swing.ImageIcon;

/**
 *
 * @author holohoi
 */
public class EachSearchMemberResult extends javax.swing.JPanel {

    public javax.swing.JCheckBox checkbox = new javax.swing.JCheckBox();
    javax.swing.JLabel avata = new javax.swing.JLabel();
    javax.swing.JLabel name = new javax.swing.JLabel();
    javax.swing.JLabel lastseen = new javax.swing.JLabel();

    public EachSearchMemberResult() {
    }

    public EachSearchMemberResult(Object[] _user) {
        this.setBackground(new java.awt.Color(255, 255, 255));
        this.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 102, 102)));

        checkbox.setBackground(new java.awt.Color(255, 255, 255));
        checkbox.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        name.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        name.setForeground(new java.awt.Color(0, 0, 0));

        lastseen.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        lastseen.setForeground(new java.awt.Color(102, 102, 102));

        init(_user);
    }

    private void init(Object[] _user) {

//        avatar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/user.png"))); // NOI18N
        avata.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/user.png")).getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)));

        name.setText(_user[1].toString());

//===============
        String objLastSeen = _user[3].toString();
        Timestamp lastseenVar = Timestamp.valueOf(objLastSeen);
        if (isRightDate(lastseenVar)) {
            String sendedAtDate = new SimpleDateFormat("hh:mm a").format(lastseenVar);
            sendedAtDate = "last seen " + sendedAtDate;
            lastseen.setText("<html><p>" + sendedAtDate + "</p></html>");
        } else {
            String sendedAtDate = new SimpleDateFormat("dd/MM/yyyy").format(lastseenVar);
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
                                .addComponent(checkbox)
                                .addContainerGap())
        );
        thisLayout.setVerticalGroup(
                thisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(thisLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(checkbox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
