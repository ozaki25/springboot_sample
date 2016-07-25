package com.example.workflow;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.List;

@Entity
public class Work {
    @Id
    @GeneratedValue
    private Long id;
    private String content;
    @OneToOne(cascade = CascadeType.PERSIST, mappedBy = "work")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private Request request;

    public Work() { };

    public Work(String content) {
        this.content = content;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Request getRequest() {
        return this.request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public String toString() {
        return "{id: " + this.getId() + ", content: " + this.getContent() + "requestId" + this.getRequestIdToString() + "}";
    }

    public static String toString(List<Work> works) {
        if(works.size() == 0) return "Work is Empty.";
        String result = "[";
        for(Work w : works) { result += w.toString() + ", "; }
        result = result.substring(0, result.length() - 2);
        result += "]";
        return result;
    }

    @JsonIgnore
    public String getRequestIdToString() {
        return this.request == null ? "" : this.request.getId().toString();
    }
}
