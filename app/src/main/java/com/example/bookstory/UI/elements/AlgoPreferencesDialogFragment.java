package com.example.bookstory.UI.elements;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.bookstory.DOMAIN.Sortables.*;
import com.example.bookstory.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AlgoPreferencesDialogFragment extends DialogFragment {

    public static String TAG = "SortPreferencesDialogHost";
    AlgoPreferencesDialogFragment.AlgoPreferencesDialogListener dialogListener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.dialog_algo_preferences, null);
        RadioGroup radioGroup = v.findViewById(R.id.radioGroup_dialogAlgoPreferences_radioGroup);

        //TODO: add reflection mechanism
        List<Sortable> sortableList = new ArrayList<>();
        sortableList.add(new BubbleSort());
        sortableList.add(new DefaultSort());
        sortableList.add(new InsertionSort());
        sortableList.add(new MergeSort());
        sortableList.add(new QuickSort());
        sortableList.add(new SelectionSort());

        Map<Integer, Sortable> map = new HashMap<>();
        for (int i = 0; i < sortableList.size(); i++) {
            RadioButton radioButton = new RadioButton(getContext());
            radioButton.setText(sortableList.get(i).getClass().getSimpleName());
            radioGroup.addView(radioButton, i);
            map.put(radioButton.getId(), sortableList.get(i));
        }
        return new AlertDialog.Builder(getContext())
                .setTitle("Выбор алгоритма сортировки")
                .setView(v)
                .setPositiveButton("Применить", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialogListener.applyAlgoPreferences(
                                        map.get(radioGroup.getCheckedRadioButtonId())
                        );
                    }
                }).create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            dialogListener = (AlgoPreferencesDialogFragment.AlgoPreferencesDialogListener) getParentFragment();
        }catch (ClassCastException e) {
            e.printStackTrace();
        }
    }

    public interface AlgoPreferencesDialogListener {
        void applyAlgoPreferences(Sortable sortable);
    }
}
