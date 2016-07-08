package com.example.workflow;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Request {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String content;
    @OneToOne(cascade = CascadeType.ALL)
    private Applicant applicant;
    @OneToOne(cascade = CascadeType.ALL)
    private Authorizer authorizer;
    @ManyToOne
    private Status status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "request")
    private List<Document> documents = new ArrayList<Document>();

    public Request() { };

    public Request(String title, String content, Status status, Applicant applicant, Authorizer authorizer) {
        this.title = title;
        this.content = content;
        this.status = status;
        this.applicant = applicant;
        this.authorizer = authorizer;
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

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Applicant getApplicant() {
        return this.applicant;
    }

    public void setApplicant(Applicant applicant) {
        this.applicant = applicant;
    }

    public Authorizer getAuthorizer() {
        return this.authorizer;
    }

    public void setAuthorizer(Authorizer authorizer) {
        this.authorizer = authorizer;
    }

    public List<Document> getDocuments() {
        return this.documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    public String toString() {
        return "{id: " + this.getId() + ", title: " + this.getTitle() + ", content: " + this.getContent() + ", status: " + this.getStatusToString() + ", applicant: " + this.getApplicantToString() + ", authorizer: " + this.getAuthorizerToString() + ", documents: " + this.documents.size() + "}";
    }

    public static String toString(List<Request> requests) {
        if(requests.size() == 0) return "Request is Empty.";
        String result = "[";
        for(Request r : requests) { result += r.toString() + ", "; }
        result = result.substring(0, result.length() - 2);
        result += "]";
        return result;
    }

    private String getStatusToString() {
        return this.status == null ? "" : this.status.toString();
    }

    private String getApplicantToString() {
        return this.applicant == null ? "" : this.applicant.toString();
    }

    private String getAuthorizerToString() {
        return this.authorizer == null ? "" : this.authorizer.toString();
    }
}
