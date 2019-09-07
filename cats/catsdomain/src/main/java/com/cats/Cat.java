package com.cats;

import java.io.Serializable;
import java.util.Date;

public class Cat implements Serializable {
    private String name = "unknown";
    private Date birth = new Date();
    private Double weight = 0.0;
    private String owner = "unknown";

    public Cat(){}

    public Cat(String name){
        this.name = name;
    }

    public Cat(String name, Date birth, Double weight, String owner){
        this.name = name;
        this.birth = birth;
        this.weight = weight;
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
