package com.example.bookstory.UI.elements;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.bookstory.DAO.relations.BookCharacterCrossRef.TypeOfParticipation;
import com.google.android.material.radiobutton.MaterialRadioButton;

public class CharacterParticipationDialogFragment extends DialogFragment {

    public static String TAG = "CharacterPseudonymsDialogHost";
    CharacterParticipationDialogListener dialogListener;
    RadioGroup radioGroup;
    MaterialRadioButton mainCharacterRb, minorCharacterRb, episodicCharacterRb;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        init();
        return new AlertDialog.Builder(getContext())
                .setMessage("Введите псевдонимы:")
                .setView(radioGroup)
                .setPositiveButton("Добавить", (dialog, which) -> {
                    if (radioGroup.getCheckedRadioButtonId() == mainCharacterRb.getId()) {
                        dialogListener.applyCharacterParticipation(TypeOfParticipation.MAIN_CHARACTER);
                    } else if (radioGroup.getCheckedRadioButtonId() == minorCharacterRb.getId()) {
                        dialogListener.applyCharacterParticipation(TypeOfParticipation.MINOR_CHARACTER);
                    } else {
                        dialogListener.applyCharacterParticipation(TypeOfParticipation.EPISODIC_CHARACTER);
                    }
                }).create();
    }

    private void init() {
        radioGroup = new RadioGroup(getContext());

        mainCharacterRb = new MaterialRadioButton(getContext());
        mainCharacterRb.setText("Главный герой");
        minorCharacterRb = new MaterialRadioButton(getContext());
        minorCharacterRb.setText("Второстепеный герой");
        episodicCharacterRb = new MaterialRadioButton(getContext());
        episodicCharacterRb.setText("Эпизодический герой");

        radioGroup.addView(mainCharacterRb);
        radioGroup.addView(minorCharacterRb);
        radioGroup.addView(episodicCharacterRb);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            dialogListener = (CharacterParticipationDialogListener) getParentFragment();
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }

    public interface CharacterParticipationDialogListener {
        void applyCharacterParticipation(TypeOfParticipation typeOfParticipation);
    }

}
