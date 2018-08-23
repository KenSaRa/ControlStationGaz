package com.kensara.stationcarcontrol.Model;

import java.util.List;

public class ProfilUser {
    String username;
    String role;
    String type;
    String name;
    String email;

    List<Pompe> pompes;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public List<Pompe> getPompes() {
        return pompes;
    }

    public void setPompes(List<Pompe> pompes) {
        this.pompes = pompes;
    }
}
