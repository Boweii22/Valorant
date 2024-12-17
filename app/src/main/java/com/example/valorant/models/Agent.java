package com.example.valorant.models;

import java.io.Serializable;
import java.util.List;


import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Agent implements Parcelable {
    @SerializedName("displayName")
    private String displayName;
    private String developerName;
    private String displayIcon, fullPortrait, fullPortraitv2;
    private String description;
    private List<String> backgroundGradientColors;

    @SerializedName("abilities")
    private List<Ability> abilities;

    @SerializedName("role")
    private Role role;

    public Agent(String displayName,String developerName, String description, String displayIcon, Role role, String fullPortrait, String fullPortraitv2, List<Ability> abilities,List<String> backgroundGradientColors ) {
        this.displayName = displayName;
        this.displayIcon = displayIcon;
        this.description = description;
        this.developerName = developerName;
        this.role = role;
        this.fullPortrait = fullPortrait;
        this.fullPortraitv2 = fullPortraitv2;
        this.abilities = abilities;
        this.backgroundGradientColors = backgroundGradientColors;
    }

    protected Agent(Parcel in) {
        displayName = in.readString();
        fullPortrait = in.readString();
        fullPortraitv2 = in.readString();
        description = in.readString();
        displayIcon = in.readString();
        developerName = in.readString();
        abilities = in.createTypedArrayList(Ability.CREATOR);
        backgroundGradientColors = in.createStringArrayList();
    }

    public static final Creator<Agent> CREATOR = new Creator<Agent>() {
        @Override
        public Agent createFromParcel(Parcel in) {
            return new Agent(in);
        }

        @Override
        public Agent[] newArray(int size) {
            return new Agent[size];
        }
    };

    public Role getRole() {
        return role;
    }
    public String getDeveloperName(){
        return developerName;
    }

    public void setRole(Role role) {
        this.role = role;
    }
    public String getName() {
        return displayName;
    }

    public String getDisplayIcon() {
        return displayIcon;
    }
    public String getFullPortrait() {
        return fullPortrait;
    }
    public String getFullPortraitV2() {
        return fullPortraitv2;
    }

    public String getDescription()  {
        return description;
    }

    public List<Ability> getAbilities() {
        return abilities;
    }

    public String[] getBackgroundGradientColors() {
        // Convert List<String> to String[] for compatibility with the gradient function
        return backgroundGradientColors.toArray(new String[0]);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(displayName);
        dest.writeString(displayIcon);
        dest.writeString(fullPortrait);
        dest.writeString(description);
        dest.writeString(fullPortraitv2);
        dest.writeString(developerName);
        dest.writeTypedList(abilities);
        dest.writeStringList(backgroundGradientColors);
    }
}
