package com.example.valorant.models;

import java.io.Serializable;
import java.util.List;

public class Character implements Serializable {
    private String displayName;
    private String description;
    private String displayIcon;
    private String fullPortrait;
    private String country;
    private List<String> abilities;

    // Getters and Setters
    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDisplayIcon() {
        return displayIcon;
    }

    public void setDisplayIcon(String displayIcon) {
        this.displayIcon = displayIcon;
    }

    public String getFullPortrait() {
        return fullPortrait;
    }

    public void setFullPortrait(String fullPortrait) {
        this.fullPortrait = fullPortrait;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<String> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<String> abilities) {
        this.abilities = abilities;
    }
}
