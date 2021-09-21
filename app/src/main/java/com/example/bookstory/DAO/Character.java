package com.example.bookstory.DAO;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Set;

@Entity
public class Character {
    @NonNull
    @PrimaryKey(autoGenerate = false)
    public String characterName;

    public String pseudonyms;
}
