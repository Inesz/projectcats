package com.cats;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * name="cats" with @Entity is needed. Otherwise: InvalidDataAccessApiUsageException cats is not mapped
 */
@Entity(name = "cats")
@Table(name = "Cats")
public class Cat implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    //@OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
    @OneToOne
    @JoinColumn(name = "fotoId")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private CatFoto catFoto;

    @Column(name = "name")
    private String name = "unknown";

    @Column(name = "birth")
    private Date birth = new Date();

    @Column(name = "weight")
    private Double weight = 0.0;

    @Column(name = "owner")
    private String owner = "unknown";

    public Cat() {
    }

    public Cat(String name) {
        this.name = name;
    }

    public Cat(String name, Date birth, Double weight, String owner) {
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void removeCatFoto() {
        if (catFoto != null) {
            this.catFoto = null;
        }
    }
}