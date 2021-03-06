package com.cal.budget.dao;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Document(collection = "Bill")
public class Bill {

    @Id
    private String id;
    private String name;
    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
    private Date dueTime;
    private double amount;
    private String category;
    @JsonProperty
    private boolean isPaid;

    public Bill() {

    }

    public Bill(String name, Date dueTime, double amount, String category) {
        this.name = name;
        this.dueTime = dueTime;
        this.amount = amount;
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDueTime() {
        return dueTime;
    }

    public void setDueTime(Date dueTime) {
        this.dueTime = dueTime;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", dueTime=" + dueTime +
                ", amount=" + amount +
                ", category='" + category + '\'' +
                ", isPaid=" + isPaid +
                '}';
    }
}
