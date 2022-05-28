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
public class User implements java.io.Serializable {

    private int id;
    private String username;
    private String password;
    private String name;
    private String email;
    private Timestamp lastSeen;
    private String image;
    private boolean gender; //true: nam, false: nu
    private boolean isActive; // True: Male, False: Female

    //Các phương thức khởi tạo, get, set.
    public User() {
    }

    public User(String username, String password, String name, String email, boolean gender) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        Long datetime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(datetime);
        this.lastSeen = timestamp;
        this.image = null;
        this.gender = gender;
        this.isActive = true;
    }

    public User(String username, String password, String name) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = null;
        Long datetime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(datetime);
        this.lastSeen = timestamp;
        this.image = null;
        this.gender = true;
        this.isActive = true;
    }

    public Object[] getObject() {
        return new Object[]{id, name, email, lastSeen.toString(), image, gender, isActive};
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Timestamp getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(Timestamp lastSeen) {
        this.lastSeen = lastSeen;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

}
