package com.example.workflow;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String uid;
    private String name;
    private String team;
    private int jobLevel;
    private boolean admin;
    private String password;

    public User() { };

    public User(String uid, String name, String team, int jobLevel, boolean admin) {
        this.uid = uid;
        this.name = name;
        this.team = team;
        this.jobLevel = jobLevel;
        this.admin = admin;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUid() {
        return this.uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeam() {
        return this.team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public int getJobLevel() {
        return this.jobLevel;
    }

    public void setJobLevel(int jobLevel) {
        this.jobLevel = jobLevel;
    }

    public boolean getAdmin() {
        return this.admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String toString() {
        return "{id: " + this.getId() + ", uid: " + this.getUid() + ", name: " + this.getName() + ", team: " + this.getTeam() + ", jobLevel: " + this.getJobLevel() + ", admin: " + this.getAdmin() + "}";
    }

    public static String toString(List<User> users) {
        if(users.size() == 0) return "User is Empty.";
        String result = "[";
        for(User u : users) {
            result += u.toString() + ", ";
        }
        result = result.substring(0, result.length() - 2);
        result += "]";
        return result;
    }
}
