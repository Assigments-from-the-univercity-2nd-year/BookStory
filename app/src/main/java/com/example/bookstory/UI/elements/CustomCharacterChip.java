package com.example.bookstory.UI.elements;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;

import com.example.bookstory.DAO.relations.BookCharacterCrossRef.TypeOfParticipation;

public class CustomCharacterChip extends CustomChip{

    private TypeOfParticipation typeOfParticipation;

    public CustomCharacterChip(@NonNull CustomChipGroup customChipGroup, String customChipText,
                               TypeOfParticipation typeOfParticipation) {
        super(customChipGroup, customChipText);
    }

    public CustomCharacterChip(Context context) {
        super(context);
    }

    public CustomCharacterChip(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomCharacterChip(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public TypeOfParticipation getTypeOfParticipation() {
        return typeOfParticipation;
    }
}
