// package com.example;

import java.sql.Timestamp;

public class Email {
    private int id;
    private int userId;
    private String content;
    private double suspicionPercentage;
    private Timestamp scannedAt;

    public Email(int id, int userId, String content, double suspicionPercentage, Timestamp scannedAt) {
        this.id = id;
        this.userId = userId;
        this.content = content;
        this.suspicionPercentage = suspicionPercentage;
        this.scannedAt = scannedAt;
    }

    @Override
    public String toString() {
        return "Email{" +
                "id=" + id +
                ", userId=" + userId +
                ", content='" + content + '\'' +
                ", suspicionPercentage=" + suspicionPercentage +
                ", scannedAt=" + scannedAt +
                '}';
    }
}
