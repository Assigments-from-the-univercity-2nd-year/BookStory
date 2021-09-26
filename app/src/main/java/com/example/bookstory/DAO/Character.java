package com.example.bookstory.DAO;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity
public class Character {
    @NonNull
    @PrimaryKey(autoGenerate = false)
    public String characterName;

    public String pseudonyms;

    public Character(@NonNull String characterName, String pseudonyms) {
        this.characterName = characterName;
        this.pseudonyms = pseudonyms;
    }

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
}
