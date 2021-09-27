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

import java.util.zip.Inflater;

public class SortPreferencesDialogFragment extends DialogFragment {

    public static String TAG = "SortPreferencesDialogHost";
    SortPreferencesDialogListener dialogListener;

    RadioGroup selectArg, selectOrder;


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.dialog_sort_preferences, null);
        selectArg = v.findViewById(R.id.radioGroup_dialogSortPref_selectArg);
        selectOrder = v.findViewById(R.id.radioGroup_dialogSortPref_selectOrder);
        Switch aSwitch = v.findViewById(R.id.switch1);

        return new AlertDialog.Builder(getContext())
                .setTitle("Сортировка")
                .setView(R.layout.dialog_sort_preferences)
                .setPositiveButton("Применить",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialogListener.applySortPreferences(
                                        selectArg.getCheckedRadioButtonId(),
                                        selectOrder.getCheckedRadioButtonId(),
                                        false);
                            }
                        }).create();
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
