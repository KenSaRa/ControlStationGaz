package com.kensara.stationcarcontrol.Model;

public class User {
    private String username;
    private String password;
    private String  email;
    private String image;

    public User(String username, String password, String email, String image) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.image = image;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
