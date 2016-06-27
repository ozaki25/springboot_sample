package com.example.workflow;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import java.util.List;

@Entity
public class Request {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String content;
    private String filename;
    @Lob
    private byte[] file;
    @ManyToOne
    private Status status;
    @ManyToOne
    private User user;

    public Request() { };

    public Request(String title, String content, String filename, byte[] file, Status status, User user) {
        this.title = title;
        this.content = content;
        this.file = file;
        this.filename = filename;
        this.status = status;
        this.user = user;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFilename() {
        return this.filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public byte[] getFile() {
        return this.file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String toString() {
        return "{id: " + this.getId() + ", title: " + this.getTitle() + ", content: " + this.getContent() + ", filename: " + this.getFilename() + ", status: " + this.getStatus().toString() + ", user: " + this.getUser().toString() + "}";
    }

    public static String toString(List<Request> requests) {
        if(requests.size() == 0) return "Request is Empty.";
        String result = "[";
        for(Request r : requests) {
            result += "{id: " + r.getId() + ", title: " + r.getTitle() + ", content: " + r.getContent() + ", filename: " + r.getFilename() + ", status: " + r.getStatus().toString() + ", user: " + r.getUser().toString() + "}, ";
        }
        result = result.substring(0, result.length() - 2);
        result += "]";
        return result;
    }
}
