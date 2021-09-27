package com.example.bookstory.UI.elements;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class CharacterPseudonymsDialogFragment extends DialogFragment {

    public static String TAG = "CharacterPseudonymsDialogHost";
    CharacterPseudonymsDialogListener dialogListener;
    EditText pseudonymsTv;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        pseudonymsTv = new EditText(getContext());
        pseudonymsTv.setLayoutParams(new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT));
        return new AlertDialog.Builder(getContext())
                .setMessage("Введите псевдонимы:")
                .setView(pseudonymsTv)
                .setPositiveButton("Добавить", (dialog, which) -> {
                    String pseudonyms = pseudonymsTv.getText().toString();
                    dialogListener.applyCharacterPseudonyms(pseudonyms);
                }).create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            dialogListener = (CharacterPseudonymsDialogListener) getParentFragment();
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }

    public interface CharacterPseudonymsDialogListener {
        void applyCharacterPseudonyms(String characterPseudonyms);
    }
}
