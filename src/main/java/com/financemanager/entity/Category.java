package com.financemanager.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.Set;
import java.util.UUID;

@Entity
@Table (name = "category")
public class Category {
    @Id
    private String id;
    private String name;

    @OneToMany(mappedBy = "category")
    private Set<Expense> expense;

    public Category() {
    }

    public Category(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
    }

    public Category(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName () {
        return name;
    }

    public String getId() {
        return id;
    }
}
