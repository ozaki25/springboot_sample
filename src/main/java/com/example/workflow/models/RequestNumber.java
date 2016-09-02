package com.example.workflow;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class RequestNumber {
    @Id
    @GeneratedValue
    private Long id;
    private int year;
    private int number;

    public RequestNumber() { };

    public RequestNumber(int year) {
        this.year = year;
        this.number = 1;
    }

    public RequestNumber(int year, int number) {
        this.year = year;
        this.number = number;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getNumber() {
        return this.number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
