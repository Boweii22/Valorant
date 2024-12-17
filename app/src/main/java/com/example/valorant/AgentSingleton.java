package com.example.valorant;

import com.example.valorant.models.Agent;

public class AgentSingleton {
    private static AgentSingleton instance;
    private Agent currentAgent;

    private AgentSingleton() {
    }

    public static AgentSingleton getInstance() {
        if (instance == null) {
            instance = new AgentSingleton();
        }
        return instance;
    }

    public void setAgent(Agent agent) {
        currentAgent = agent;
    }

    public Agent getAgent() {
        return currentAgent;
    }
}

