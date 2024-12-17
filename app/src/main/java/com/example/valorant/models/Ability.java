package com.example.valorant.models;


import java.io.Serial;
import java.io.Serializable;


import android.os.Parcel;
import android.os.Parcelable;

public class Ability implements Parcelable {
    private String displayName;
    private String description;
    private String displayIcon;

    public Ability(String displayName, String description, String displayIcon) {
        this.displayName = displayName;
        this.description = description;
        this.displayIcon = displayIcon;
    }

    protected Ability(Parcel in) {
        displayName = in.readString();
        description = in.readString();
        displayIcon = in.readString();
    }

    public static final Creator<Ability> CREATOR = new Creator<Ability>() {
        @Override
        public Ability createFromParcel(Parcel in) {
            return new Ability(in);
        }

        @Override
        public Ability[] newArray(int size) {
            return new Ability[size];
        }
    };

    public String getDisplayName() {
        return displayName;
    }

    public String getDescription() {
        return description;
    }

    public String getDisplayIcon() {
        return displayIcon;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(displayName);
        dest.writeString(description);
        dest.writeString(displayIcon);
    }
}
