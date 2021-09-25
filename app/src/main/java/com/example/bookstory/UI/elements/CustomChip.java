package com.example.bookstory.UI.elements;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.example.bookstory.R;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;

public class CustomChip extends Chip {
    private String customChipText;
    CustomChipGroup customChipGroup;

    public CustomChip(CustomChipGroup customChipGroup, String customChipText) {
        super(customChipGroup.getContext());
        this.customChipGroup = customChipGroup;

        this.customChipText = customChipText;
        ChipDrawable chipDrawable = ChipDrawable.createFromAttributes(
                customChipGroup.getContext(),
                null,
                0,
                R.style.Widget_MaterialComponents_Chip_Entry);
        setChipDrawable(chipDrawable);
        setCheckable(false);
        setClickable(false);
        setChipStartPadding(3f);
        setPadding(60, 10, 60, 10);
        setText(customChipText);

        setOnCloseIconClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customChipGroup.removeView(v);
            }
        });
    }

    public CustomChip(Context context) {
        super(context);
    }

    public CustomChip(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomChip(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public String getCustomChipText() {
        return customChipText;
    }
}
