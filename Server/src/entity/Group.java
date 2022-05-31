/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

/**
 *
 * @author holohoi
 */
public class Group implements java.io.Serializable {

    private int id;
    private String name;
    private Timestamp createAt;
    private User createBy;
    private boolean isActive; // True: Male, False: Female

    //Các phương thức khởi tạo, get, set.
    public Group() {
    }

    public Group(String name, User creator) {
        this.name = name;
        Long datetime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(datetime);
        this.createAt = timestamp;
        this.createBy = creator;
        this.isActive = true;
    }

    public Object[] getObject() {
        return new Object[]{id, name, createAt.toString(), createBy.getId(), isActive};
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Timestamp createAt) {
        this.createAt = createAt;
    }

    public User getCreateBy() {
        return createBy;
    }

    public void setCreateBy(User createBy) {
        this.createBy = createBy;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
}
