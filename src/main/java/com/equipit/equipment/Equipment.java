package com.equipit.equipment;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;

@Entity
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String category;
    private boolean available = true;
    private String borrowerFirstName;
    private String borrowerLastName;
    private LocalDate borrowedAt;
    private LocalDate lastReturnedAt;

    protected Equipment() { }

    public Equipment(String name, String category) {
        this.name = name;
        this.category = category;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }
    public String getBorrowerFirstName() { return borrowerFirstName; }
    public void setBorrowerFirstName(String borrowerFirstName) { this.borrowerFirstName = borrowerFirstName; }
    public String getBorrowerLastName() { return borrowerLastName; }
    public void setBorrowerLastName(String borrowerLastName) { this.borrowerLastName = borrowerLastName; }
    public LocalDate getBorrowedAt() { return borrowedAt; }
    public void setBorrowedAt(LocalDate borrowedAt) { this.borrowedAt = borrowedAt; }
    public LocalDate getLastReturnedAt() { return lastReturnedAt; }
    public void setLastReturnedAt(LocalDate lastReturnedAt) { this.lastReturnedAt = lastReturnedAt; }
}