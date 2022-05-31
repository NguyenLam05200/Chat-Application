/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.sql.Timestamp;
import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author holohoi
 */
public class Group_User implements java.io.Serializable {

    private User userID;
    private Group groupID;
    private Timestamp importAt;
    private User importBy;
    private boolean isActive;

    public Group_User() {
    }

    public Group_User(User user, Group group, User importer) {
        this.userID = user;
        this.groupID = group;
        Long datetime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(datetime);
        this.importAt = timestamp;
        this.importBy = importer;
        this.isActive = true;
    }

    public User getUserID() {
        return userID;
    }

    public void setUserID(User userID) {
        this.userID = userID;
    }

    public Group getGroupID() {
        return groupID;
    }

    public void setGroupID(Group groupID) {
        this.groupID = groupID;
    }

    public Timestamp getImportAt() {
        return importAt;
    }

    public void setImportAt(Timestamp importAt) {
        this.importAt = importAt;
    }

    public User getImportBy() {
        return importBy;
    }

    public void setImportBy(User importBy) {
        this.importBy = importBy;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
}
