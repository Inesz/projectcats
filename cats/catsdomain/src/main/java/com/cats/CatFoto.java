package com.cats;

import javax.persistence.*;

@Entity(name="catFoto")
@Table(name="CatFoto")
public class CatFoto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="catId")
    private int catId;
    @Column(name="comment")
    private String comment;
    @Column(name="oldName")
    private String oldName;
    @Column(name="newName")
    private String newName;
    @Column(name="size")
    private long size;
    @Column(name="type")
    private String type;

    public CatFoto(){ }

    public CatFoto(String oldName, String newName, long size, String type){
        this.oldName = oldName;
        this.newName = newName;
        this.size = size;
        this.type = type;
    }

    public CatFoto(int catId, String comment, String oldName, String newName, long size, String type){
        this.catId = catId;
        this.comment = comment;
        this.oldName = oldName;
        this.newName = newName;
        this.size = size;
        this.type = type;
    }

    public int getCatid() {
        return catId;
    }

    public void setCatid(int catId) {
        this.catId = catId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getOldname() {
        return oldName;
    }

    public void setOldname(String oldName) {
        this.oldName = oldName;
    }

    public String getNewname() {
        return newName;
    }

    public void setNewname(String newName) {
        this.newName = newName;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
