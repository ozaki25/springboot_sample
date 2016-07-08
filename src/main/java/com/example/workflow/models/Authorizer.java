package com.example.workflow;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Authorizer {
    @Id
    @GeneratedValue
    private Long id;
    private String uid;
    private String name;
    private String team;
    @OneToOne(cascade = CascadeType.PERSIST, mappedBy = "authorizer")
    private Request request;

    public Authorizer() { };

    public Authorizer(String uid, String name, String team) {
        this.uid = uid;
        this.name = name;
        this.team = team;
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

    protected Request getRequest() {
        return this.request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public String toString() {
        return "{id: " + this.getId() + ", uid: " + this.getUid() + ", name: " + this.getName() + ", team: " + this.getTeam() +  "}";
    }

    public static String toString(List<Authorizer> authorizers) {
        if(authorizers.size() == 0) return "Authorizer is Empty.";
        String result = "[";
        for(Authorizer a : authorizers) { result += a.toString() + ", "; }
        result = result.substring(0, result.length() - 2);
        result += "]";
        return result;
    }

    public String getRequestIdToString() {
        return this.request == null ? "" : this.request.getId().toString();
    }
}
