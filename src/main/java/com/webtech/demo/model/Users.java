package com.webtech.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Users {

    @Id @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private long id;
    private String vorname;
    private String nachname;
    private String username;
    private String email;

    public Users(long id, String vorname, String nachname, String username, String email) {
        this.id = id;
        this.vorname = vorname;
        this.nachname = nachname;
        this.username = username;
        this.email = email;
    }

    public long getUserID() {
        return id;
    }

    public void setUserID(long id) {
        this.id = id;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + id +
                ", vorname='" + vorname + '\'' +
                ", nachname='" + nachname + '\'' +
                ", username=" + username +
                ", email=" + email +
                '}';
    }


}