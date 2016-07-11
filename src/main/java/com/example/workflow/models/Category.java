package com.example.workflow;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Category {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String code;
    @OneToMany(cascade=CascadeType.ALL, mappedBy = "category")
    private List<Request> requests;

    public Category() { };

    public Category(String name, String code) {
        this.name = name;
        this.code = code;
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

    public String toString() {
        return "{id: " + this.getId() + ", name: " + this.getName() + ", code: " + this.getCode() + "}";
    }

    public static String toString(List<Category> caterogies) {
        if(caterogies.size() == 0) return "Category is Empty.";
        String result = "[";
        for(Category c : caterogies) {
            result += c.toString() + ", ";
        }
        result = result.substring(0, result.length() - 2);
        result += "]";
        return result;
    }
}
