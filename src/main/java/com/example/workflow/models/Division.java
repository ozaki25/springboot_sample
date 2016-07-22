package com.example.workflow;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Division {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String code;
    @ManyToOne(cascade = CascadeType.MERGE)
    private Category category;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "division")
    private List<Request> requests = new ArrayList<Request>();

    public Division() { };

    public Division(String name, String code, Category category) {
        this.name = name;
        this.code = code;
        this.category = category;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String toString() {
        return "{id: " + this.getId() + ", name: " + this.getName() + ", code: " + this.getCode() + ", category: " + this.getCategoryToString() + "}";
    }

    public static String toString(List<Division> divisions) {
        if(divisions.size() == 0) return "Division is Empty.";
        String result = "[";
        for(Division d : divisions) {
            result += d.toString() + ", ";
        }
        result = result.substring(0, result.length() - 2);
        result += "]";
        return result;
    }

    private String getCategoryToString() {
        return this.category == null ? "" : this.category.toString();
    }
}
