package com.example.workflow;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.List;

@Entity
public class History {
    @Id
    @GeneratedValue
    private Long id;
    private String uid;
    private String name;
    private String team;
    private String action;
    @JsonIgnore
    @ManyToOne
    private Request request;
    private Date createdDate;

    public History() { };

    public History(String uid, String name, String team, Request request, String action) {
        this.uid = uid;
        this.name = name;
        this.team = team;
        this.action = action;
        this.request = request;
        this.createdDate = new Date();
    }

    public History(User user, Request request, String action) {
        this.uid = user.getUid();
        this.name = user.getName();
        this.team = user.getTeam();
        this.action = action;
        this.request = request;
        this.createdDate = new Date();
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

    public String getAction() {
        return this.action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Request getRequest() {
        return this.request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public String toString() {
        return "{id: " + this.getId() + ", uid: " + this.getUid() + ", name: " + this.getName() + ", team: " + this.getTeam() + "requestId" + this.getRequestIdToString() + "}";
    }

    public static String toString(List<History> histories) {
        if(histories.size() == 0) return "History is Empty.";
        String result = "[";
        for(History a : histories) { result += a.toString() + ", "; }
        result = result.substring(0, result.length() - 2);
        result += "]";
        return result;
    }

    @JsonIgnore
    public String getRequestIdToString() {
        return this.request == null ? "" : this.request.getId().toString();
    }
}
