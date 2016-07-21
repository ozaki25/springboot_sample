package com.example.workflow;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.List;

@Entity
public class Receptnist {
    @Id
    @GeneratedValue
    private Long id;
    private String uid;
    private String name;
    private String team;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private Category category;

    public Receptnist() { };

    public Receptnist(String uid, String name, String team) {
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

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String toString() {
        return "{id: " + this.getId() + ", uid: " + this.getUid() + ", name: " + this.getName() + ", team: " + this.getTeam() + "categoryId: " + this.getCategoryIdToString() + "}";
    }

    public static String toString(List<Receptnist> receptnists) {
        if(receptnists.size() == 0) return "Receptnist is Empty.";
        String result = "[";
        for(Receptnist r : receptnists) { result += r.toString() + ", "; }
        result = result.substring(0, result.length() - 2);
        result += "]";
        return result;
    }

    public String getCategoryIdToString() {
        return this.category == null ? "" : this.category.getId().toString();
    }
}
