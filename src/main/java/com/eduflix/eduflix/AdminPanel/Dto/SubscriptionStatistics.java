package com.eduflix.eduflix.AdminPanel.Dto;

import lombok.Data;

@Data
public class SubscriptionStatistics {
    private String year;
    private String month;
    private int number;

    @Override
    public String toString() {
        return "SubscriptionStatistics{" +
                "year='" + year + '\'' +
                ", month='" + month + '\'' +
                ", number=" + number +
                '}';
    }
}
