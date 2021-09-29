package com.example.bookstory.UI.elements;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.bookstory.R;

public class SortPreferencesDialogFragment extends DialogFragment {

    public static String TAG = "SortPreferencesDialogHost";
    SortPreferencesDialogListener dialogListener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.dialog_sort_preferences, null);
        RadioGroup selectArg, selectOrder;
        Switch aSwitch = v.findViewById(R.id.switch1);
        selectArg = v.findViewById(R.id.radioGroup_dialogSortPref_selectArg);
        selectOrder = v.findViewById(R.id.radioGroup_dialogSortPref_selectOrder);
        return new AlertDialog.Builder(getContext())
                .setTitle("Сортировка")
                .setView(v)
                .setPositiveButton("Применить",
                        (dialog, which) -> dialogListener.applySortPreferences(
                                selectArg.getCheckedRadioButtonId(),
                                selectOrder.getCheckedRadioButtonId(),
                                aSwitch.isChecked())).create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            dialogListener = (SortPreferencesDialogListener) getParentFragment();
        }catch (ClassCastException e) {
            e.printStackTrace();
        }
    }

    public interface SortPreferencesDialogListener {
        void applySortPreferences(int argId, int sortById, boolean isInclude);
    }
}
