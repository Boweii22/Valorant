package com.example.valorant.network;

import com.example.valorant.models.Agent;

import java.util.List;

public class ApiResponse {
    private String status;
    private List<Agent> data;

    // Getters and Setters

    public String getStatus() {
        return status;
    }

    public List<Agent> getData() {
        return data;
    }
}

