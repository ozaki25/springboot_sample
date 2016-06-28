package com.example.workflow;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Status {
    @Id
    @GeneratedValue
    private Long id;
    private int code;
    private String name;
    @OneToMany(cascade=CascadeType.ALL, mappedBy = "status")
    private List<Request> requests;

    public Status() { };

    public Status(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return "{id: " + this.getId() + ", code: " + this.getCode() + ", name: " + this.getName() + "}";
    }

    public static String toString(List<Status> statusList) {
        if(statusList.size() == 0) return "Status is Empty.";
        String result = "[";
        for(Status s : statusList) {
            result += s.toString() + ", ";
        }
        result = result.substring(0, result.length() - 2);
        result += "]";
        return result;
    }
}
