package com.example.bookstory.UI.elements;

import android.content.Context;
import android.util.ArraySet;
import android.util.AttributeSet;
import android.view.View;

import com.google.android.material.chip.ChipGroup;

import java.util.Set;

public class CustomChipGroup extends ChipGroup {
    Set<CustomChip> chips = new ArraySet<>();

    public CustomChipGroup(Context context) {
        super(context);
    }

    public CustomChipGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomChipGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void addView(View child) {
        super.addView(child);
        if (child instanceof CustomChip) {
            chips.add((CustomChip) child);
        }
    }

    @Override
    public void removeView(View view) {
        super.removeView(view);
        chips.remove(view);
    }

    public Set<CustomChip> getChips() {
        return chips;
    }
}
