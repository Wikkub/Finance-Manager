package com.financemanager.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "expense")
public class Expense {
    @Id
    private String id;
    private int amount;
    @Column(name = "category_id")
    private String categoryId;
    @Column(name = "date_added")
    private Date dateAdded;
    private String comment;

    public Expense(){
    }

    public Expense(int amount, Category category, Date dateAdded, String comment){
        this.id = UUID.randomUUID().toString();
        //this.category_id = category;
        this.dateAdded = new Date();
        this.comment = comment;
    }

    public Expense(String id, int amount, Date dateAdded, String comment){
        this.id = id;
        this.dateAdded = new Date();
        this.comment = comment;
    }
}
