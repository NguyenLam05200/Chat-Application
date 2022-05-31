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
public class EachMsgContact extends javax.swing.JPanel {

    int id;
    boolean isUser;
//    User contact;
//    Message msgContact;

    javax.swing.JLabel avatar;
    javax.swing.JLabel nameContact;
    javax.swing.JLabel lastSeenContact;
    javax.swing.JLabel contentMsgContact;

    boolean isClicked = false;

    public EachMsgContact() {
    }

    public EachMsgContact(Object[] msgContact, Object[] contact, int index) {
        this.id = index;
        this.isUser = isUser(contact);
        String nameUser = contact[1].toString();

        avatar = new javax.swing.JLabel();
        avatar.setForeground(new java.awt.Color(0, 0, 0));
        avatar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        avatar.setPreferredSize(new java.awt.Dimension(34, 34));

        if (isUser) {
            avatar.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/user.png")).getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH)));
        } else {
            avatar.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/group.png")).getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH)));
        }

        nameContact = new javax.swing.JLabel();
        nameContact.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        nameContact.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        nameContact.setText(nameUser);

        lastSeenContact = new javax.swing.JLabel();
        lastSeenContact.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        lastSeenContact.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        if (isUser) {
            String objLastSeen = contact[3].toString();
            Timestamp lastseen = Timestamp.valueOf(objLastSeen);

            if (isRightDate(lastseen)) {
                String sendedAtDate = new SimpleDateFormat("hh:mm a").format(lastseen);
                lastSeenContact.setText(sendedAtDate);
            } else {
                String sendedAtDate = new SimpleDateFormat("dd/MM/yyyy").format(lastseen);
                lastSeenContact.setText(sendedAtDate);
            }
        } else {
            lastSeenContact.setText("group");
        }

        contentMsgContact = new javax.swing.JLabel();
        contentMsgContact.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        contentMsgContact.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        if (msgContact != null && msgContact.length != 0) {
            String content = msgContact[1].toString();
            contentMsgContact.setText(content);
        } else {
            contentMsgContact.setText("");
        }

        setColorNotClick(false);
        this.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        this.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Client.dashboard.requestListMsgChat(isUser, contact[0]);
                Client.curContact = contact;

                int beforeClick = ListMsgContactPanel.curClick;
                if (beforeClick != -1) {
                    ListMsgContactPanel.listMsgContactPanel.get(beforeClick).setColorNotClick(false);
                }
                if (isUser) {
                    String objLastSeen = contact[3].toString();
                    Timestamp lastseen = Timestamp.valueOf(objLastSeen);
                    ListMsgContactPanel.setCurClick(id, nameUser, lastseen);
                } else {
                    ListMsgContactPanel.setCurClick(id, nameUser, null);

                }
                setColorClick();
            }
        });

//        avatar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/user.png"))); // NOI18N
//================
        javax.swing.GroupLayout eachMessageLayout = new javax.swing.GroupLayout(this);
        this.setLayout(eachMessageLayout);
        eachMessageLayout.setHorizontalGroup(
                eachMessageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(eachMessageLayout.createSequentialGroup()
                                .addComponent(avatar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(eachMessageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(eachMessageLayout.createSequentialGroup()
                                                .addComponent(nameContact, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lastSeenContact)
                                                .addGap(15, 15, 15))
                                        .addComponent(contentMsgContact, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        eachMessageLayout.setVerticalGroup(
                eachMessageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(avatar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(eachMessageLayout.createSequentialGroup()
                                .addGroup(eachMessageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(nameContact, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lastSeenContact))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(contentMsgContact, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 12, Short.MAX_VALUE))
        );
    }

    public EachMsgContact(Object[] contact, int index) {
        String nameUser = contact[1].toString();
        this.id = index;
        this.isUser = isUser(contact);

        this.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        avatar = new javax.swing.JLabel();
        avatar.setForeground(new java.awt.Color(0, 0, 0));
        avatar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        avatar.setPreferredSize(new java.awt.Dimension(34, 34));
        if (isUser) {
            avatar.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/user.png")).getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH)));
        } else {
            avatar.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/group.png")).getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH)));
        }
        nameContact = new javax.swing.JLabel();
        nameContact.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        nameContact.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        nameContact.setText(nameUser);

        lastSeenContact = new javax.swing.JLabel();
        lastSeenContact.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        lastSeenContact.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lastSeenContact.setText("");

        contentMsgContact = new javax.swing.JLabel();
        contentMsgContact.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        contentMsgContact.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        String sendedAtDate;
        if (isUser) {
            String objLastSeen = contact[3].toString();
            Timestamp lastseen = Timestamp.valueOf(objLastSeen);
            if (isRightDate(lastseen)) {
                sendedAtDate = new SimpleDateFormat("hh:mm a").format(lastseen);
            } else {
                sendedAtDate = new SimpleDateFormat("dd/MM/yyyy").format(lastseen);
            }
            sendedAtDate = "last seen " + sendedAtDate;
        } else {
            sendedAtDate = "group";
        }
        contentMsgContact.setText(sendedAtDate);

        setColorNotClick(true);
        this.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
//                handleClickEachMsgContact(this.getName());
//                System.out.println("Click contact " + contact.getName());

                Client.dashboard.requestListMsgChat(isUser, contact[0]);
                Client.curContact = contact;

                int beforeClick = ListMsgContactPanel.curClick;
                if (beforeClick != -1) {
                    ListMsgContactPanel.listMsgContactPanel.get(beforeClick).setColorNotClick(true);
                }
                if (isUser) {
                    String objLastSeen = contact[3].toString();
                    Timestamp lastseen = Timestamp.valueOf(objLastSeen);
                    ListMsgContactPanel.setCurClick(id, nameUser, lastseen);
                } else {
                    ListMsgContactPanel.setCurClick(id, nameUser, null);
                }
                setColorClick();
            }
        });

        javax.swing.GroupLayout eachMessageLayout = new javax.swing.GroupLayout(this);
        this.setLayout(eachMessageLayout);
        eachMessageLayout.setHorizontalGroup(
                eachMessageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(eachMessageLayout.createSequentialGroup()
                                .addComponent(avatar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(eachMessageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(eachMessageLayout.createSequentialGroup()
                                                .addComponent(nameContact, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lastSeenContact)
                                                .addGap(15, 15, 15))
                                        .addComponent(contentMsgContact, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        eachMessageLayout.setVerticalGroup(
                eachMessageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(avatar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(eachMessageLayout.createSequentialGroup()
                                .addGroup(eachMessageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(nameContact, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lastSeenContact))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(contentMsgContact, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 12, Short.MAX_VALUE))
        );
    }

    private void init(Object[] contact) {
        setColorNotClick(false);
        this.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        this.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Client.dashboard.requestListMsgChat(isUser, contact[0]);
                Client.curContact = contact;

                int beforeClick = ListMsgContactPanel.curClick;
                if (beforeClick != -1) {
                    ListMsgContactPanel.listMsgContactPanel.get(beforeClick).setColorNotClick(false);
                }
//                ListMsgContactPanel.setCurClick(id,);
                setColorClick();
            }
        });

//        avatar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/user.png"))); // NOI18N
//================
        javax.swing.GroupLayout eachMessageLayout = new javax.swing.GroupLayout(this);
        this.setLayout(eachMessageLayout);
        eachMessageLayout.setHorizontalGroup(
                eachMessageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(eachMessageLayout.createSequentialGroup()
                                .addComponent(avatar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(eachMessageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(eachMessageLayout.createSequentialGroup()
                                                .addComponent(nameContact, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lastSeenContact)
                                                .addGap(15, 15, 15))
                                        .addComponent(contentMsgContact, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        eachMessageLayout.setVerticalGroup(
                eachMessageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(avatar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(eachMessageLayout.createSequentialGroup()
                                .addGroup(eachMessageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(nameContact, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lastSeenContact))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(contentMsgContact, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 12, Short.MAX_VALUE))
        );
    }

//    boolean isRightDate() {
//        java.sql.Date date1 = new java.sql.Date(System.currentTimeMillis());
//        Timestamp date2 = msgContact.getSendAt();
//        return date1.getDay() == date2.getDay() && date1.getMonth() == date2.getMonth() && date1.getYear() == date2.getYear();
//    }
    boolean isRightDate(Timestamp date2) {
        java.sql.Date date1 = new java.sql.Date(System.currentTimeMillis());
        return date1.getDay() == date2.getDay() && date1.getMonth() == date2.getMonth() && date1.getYear() == date2.getYear();
    }

    boolean isUser(Object[] contact) {
        return contact.length == 7;
    }

    public void setColorNotClick(boolean isSearch) {
        this.setBackground(new java.awt.Color(255, 255, 255));

        nameContact.setForeground(new java.awt.Color(0, 0, 0));
        lastSeenContact.setForeground(new java.awt.Color(102, 102, 102));
        contentMsgContact.setForeground(new java.awt.Color(102, 102, 102));

//        if (isSearch) {
//            contentMsgContact.setForeground(new java.awt.Color(102, 102, 102));
//        } else {
//            if (msgContact.getSendBy().getId() == contact.getId() && msgContact.getSeenAt() == null) {
//                contentMsgContact.setForeground(new java.awt.Color(0, 153, 255));
//            } else {
//                contentMsgContact.setForeground(new java.awt.Color(102, 102, 102));
//            }
//        }
    }

    public void setColorClick() {
        this.setBackground(new java.awt.Color(65, 159, 217));

        java.awt.Color colorTex = new java.awt.Color(255, 255, 255);

        nameContact.setForeground(colorTex);
        lastSeenContact.setForeground(colorTex);

        contentMsgContact.setForeground(colorTex);
    }
}
