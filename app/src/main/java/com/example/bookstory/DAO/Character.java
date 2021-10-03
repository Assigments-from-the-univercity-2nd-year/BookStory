package com.example.bookstory.DAO;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity
public class Character implements Parcelable {
    @NonNull
    @PrimaryKey(autoGenerate = false)
    public String characterName;

    public String pseudonyms;

    public Character(@NonNull String characterName, String pseudonyms) {
        this.characterName = characterName;
        this.pseudonyms = pseudonyms;
    }

    protected Character(Parcel in) {
        characterName = in.readString();
        pseudonyms = in.readString();
    }

    public static final Creator<Character> CREATOR = new Creator<Character>() {
        @Override
        public Character createFromParcel(Parcel in) {
            return new Character(in);
        }

        @Override
        public Character[] newArray(int size) {
            return new Character[size];
        }
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Character character = (Character) o;
        return characterName.equals(character.characterName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(characterName);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(characterName);
        dest.writeString(pseudonyms);
    }
}
