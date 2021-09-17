package com.example.bookstory.DAO;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Set;

@Entity
public class Character {
    @PrimaryKey
    String characterName;

    Set<String> pseudonyms;
}
