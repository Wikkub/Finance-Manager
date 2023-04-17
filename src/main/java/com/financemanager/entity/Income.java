package com.financemanager.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "income")
public class Income {
    @Id
    private String id;
    private int amount;
    @Column (name = "date_added")
    private LocalDate dateAdded;
    private String comment;

    public Income(){
    }

    public Income(int amount, LocalDate dateAdded, String comment){
        this.id = UUID.randomUUID().toString();
        this.amount = amount;
        this.dateAdded = dateAdded;
        this.comment = comment;
    }

    public Income(String id, int amount, LocalDate dateAdded, String comment){
        this.id = id;
        this.amount = amount;
        this.dateAdded = dateAdded;
        this.comment = comment;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setDateAdded(LocalDate dateAdded) {
        this.dateAdded = dateAdded;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Income{" +
                "id='" + id + '\'' +
                ", amount=" + amount +
                ", dateAdded=" + dateAdded +
                ", comment='" + comment + '\'' +
                '}';
    }
}
