package com.financemanager.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "income")
public class Income {
    @Id

    private String id;
    private int amount;
    @Column (name = "date_added")
    private Date dateAdded;
    private String comment;

    public Income(){
    }

    public Income(int amount, Date dateAdded, String comment){
        this.id = UUID.randomUUID().toString();
        this.dateAdded = new Date();
        this.comment = comment;
    }

    public Income(String id, int amount, Date dateAdded, String comment){
        this.id = id;
        this.dateAdded = new Date();
        this.comment = comment;
    }

}
