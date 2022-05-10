/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.sql.Timestamp;

/**
 *
 * @author holohoi
 */
public class Message implements java.io.Serializable {

    private int id;
    private String content;
    private Timestamp sendAt;
    private User sendBy;
    private User sendTo;
    private boolean isAvailable;
    private Timestamp seenAt;

    public Message() {
    }

    public Message(String content, User sendBy, User sendTo) {
        this.content = content;
        this.sendBy = sendBy;
        this.sendTo = sendTo;
    }

    public Object[] getObject() {
        return new Object[]{id, content, sendAt.toString(), sendBy.getId(), sendTo.getId(), isAvailable, seenAt};
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

    public User getSendTo() {
        return sendTo;
    }

    public void setSendTo(User sendTo) {
        this.sendTo = sendTo;
    }

    public boolean isIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public Timestamp getSeenAt() {
        return seenAt;
    }

    public void setSeenAt(Timestamp seenAt) {
        this.seenAt = seenAt;
    }

}
