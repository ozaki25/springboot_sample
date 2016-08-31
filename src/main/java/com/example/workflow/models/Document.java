package com.example.workflow;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import java.util.List;

@Entity
@JsonIgnoreProperties({"file"})
public class Document {
    @Id
    @GeneratedValue
    private Long id;
    private String filename;
    @Lob
    private byte[] file;
    @ManyToOne
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private Request request;

    public Document() { };

    public Document(String filename, byte[] file) {
        this.filename = filename;
        this.file = file;
    }

    public Document(String filename, byte[] file, Request request) {
        this.filename = filename;
        this.file = file;
        this.request = request;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Request getRequest() {
        return this.request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public String toString() {
        return "{id: " + this.getId() + ", filename: " + this.getFilename() + ", request: " + this.getRequestIdToString() + "}";
    }

    public static String toString(List<Document> documents) {
        if(documents.size() == 0) return "Document is Empty.";
        String result = "[";
        for(Document d : documents) {
            result += d.toString() + ", ";
        }
        result = result.substring(0, result.length() - 2);
        result += "]";
        return result;
    }

    private String getRequestIdToString() {
        return this.request == null ? "" : this.request.getId().toString();
    }
}
