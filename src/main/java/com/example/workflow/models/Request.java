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
    private String reqId;
    private String title;
    private String content;
    @OneToOne(cascade = CascadeType.ALL)
    private Applicant applicant;
    @OneToOne(cascade = CascadeType.ALL)
    private Authorizer authorizer;
    @OneToOne(cascade = CascadeType.ALL)
    private Work work;
    @ManyToOne
    private Status status;
    @ManyToOne
    private Division division;
    @ManyToOne
    private Category category;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "request")
    private List<Document> documents = new ArrayList<Document>();

    public Request() { };

    public Request(String title, String content, Status status, Applicant applicant, Authorizer authorizer, Work work, Division division) {
        this.title = title;
        this.content = content;
        this.status = status;
        this.applicant = applicant;
        this.authorizer = authorizer;
        this.work = work;
        this.division = division;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReqId() {
        return this.reqId;
    }

    public void setReqId(String reqId) {
        this.reqId = reqId;
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

    public Division getDivision() {
        return this.division;
    }

    public void setDivision(Division division) {
        this.division = division;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
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

    public Work getWork() {
        return this.work;
    }

    public void setWork(Work work) {
        this.work = work;
    }

    public List<Document> getDocuments() {
        return this.documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    public String toString() {
        return "{id: " + this.getId() + ", reqId: " + this.getReqId() + ", title: " + this.getTitle() + ", content: " + this.getContent() + ", status: " + this.getStatusToString() + ", division: " + this.getDivisionToString() + ", applicant: " + this.getApplicantToString() + ", authorizer: " + this.getAuthorizerToString() + ", work: " + this.getWorkToString() + ", documents: " + this.documents.size() + "}";
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

    private String getDivisionToString() {
        return this.division == null ? "" : this.division.toString();
    }

    private String getApplicantToString() {
        return this.applicant == null ? "" : this.applicant.toString();
    }

    private String getAuthorizerToString() {
        return this.authorizer == null ? "" : this.authorizer.toString();
    }

    private String getWorkToString() {
        return this.work == null ? "" : this.work.toString();
    }
}
