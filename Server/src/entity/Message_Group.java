/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import dao.UserDAO;
import java.sql.Timestamp;

/**
 *
 * @author holohoi
 */
public class Message_Group implements java.io.Serializable {

    private int id;
    private String content;
    private Timestamp sendAt;
    private User sendBy;
    private Group sendTo;
    private boolean isAvailable;

    public Message_Group() {
    }

    public Message_Group(String content, User sendBy, Group sendTo) {
        this.content = content;
        this.sendBy = sendBy;
        this.sendTo = sendTo;

        Timestamp curTime = new Timestamp(System.currentTimeMillis());
        this.sendAt = curTime;
        this.isAvailable = true;
    }

    public Object[] getObject(User you) {
        if (sendBy.getId() == you.getId()) {
            return new Object[]{id, content, sendAt.toString(), you.getId(), sendTo.getId(), isAvailable};
        } else {
            User friend = UserDAO.findOneById(sendBy.getId());
            return new Object[]{id, content, sendAt.toString(), friend.getName(), sendTo.getId(), isAvailable};
        }
    }

    public Object[] getObject(User from, boolean isDeliver) {
        return new Object[]{id, content, sendAt.toString(), from.getName(), sendTo.getId(), isAvailable};
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getSendAt() {
        return sendAt;
    }

    public void setSendAt(Timestamp sendAt) {
        this.sendAt = sendAt;
    }

    public User getSendBy() {
        return sendBy;
    }

    public void setSendBy(User sendBy) {
        this.sendBy = sendBy;
    }

    public Group getSendTo() {
        return sendTo;
    }

    public void setSendTo(Group sendTo) {
        this.sendTo = sendTo;
    }

    public boolean isIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
}
