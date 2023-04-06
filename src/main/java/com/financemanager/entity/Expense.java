package com.financemanager.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "expense")
public class Expense {
    @Id
    private String id;
    private int amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name="category_id", referencedColumnName="id", nullable=false, unique=true)
    private Category category;

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
