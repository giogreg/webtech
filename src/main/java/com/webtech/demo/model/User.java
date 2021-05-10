package com.webtech.demo.model;

public class User {

    private long userID;
    private String vorname;
    private String nachname;
    private String username;
    private String email;

    public User(long userID, String vorname, String nachname, String username, String email) {
        this.userID = userID;
        this.vorname = vorname;
        this.nachname = nachname;
        this.username = username;
        this.email = email;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
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
        return "Url{" +
                "userID=" + userID +
                ", vorname='" + vorname + '\'' +
                ", nachname='" + nachname + '\'' +
                ", username=" + username +
                ", email=" + email +
                '}';
    }


}
