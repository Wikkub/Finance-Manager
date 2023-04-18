package com.financemanager.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
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
    private LocalDate dateAdded;
    private String comment;

    public Expense(){
    }

    public Expense(int amount, Category category, LocalDate dateAdded, String comment){
        this.id = UUID.randomUUID().toString();
        this.amount = amount;
        this.category = category;
        this.dateAdded = dateAdded;
        this.comment = comment;
    }

//    public Expense(String id, int amount, Category categoryId, Date dateAdded, String comment){
//        this.id = id;
//        this.amount = amount;
//        this.category = categoryId;
//        this.dateAdded = new Date();
//        this.comment = comment;
//    }

    public String getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }

    public Category getCategory() {
        return category;
    }

    public LocalDate getDateAdded() {
        return dateAdded;
    }

    public String getComment() {
        return comment;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setDateAdded(LocalDate dateAdded) {
        this.dateAdded = dateAdded;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "id='" + id + '\'' +
                ", amount=" + amount +
                ", category=" + category +
                ", dateAdded=" + dateAdded +
                ", comment='" + comment + '\'' +
                '}';
    }
}
