package com.example.bookstory.UI.elements;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.bookstory.R;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.Inflater;

public class SortPreferencesDialogFragment extends DialogFragment {

    public static String TAG = "SortPreferencesDialogHost";
    SortPreferencesDialogListener dialogListener;
    View root;
    RadioGroup selectArg, selectOrder;

    /*@Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.dialog_sort_preferences, container, false);

        selectArg = root.findViewById(R.id.radioGroup_dialogSortPref_selectArg);
        selectOrder = root.findViewById(R.id.radioGroup_dialogSortPref_selectOrder);

        View.OnClickListener onClickListenerArg = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioId = selectArg.getCheckedRadioButtonId();
                //Toast.makeText(getContext(), String.valueOf(radioId), Toast.LENGTH_LONG).show();
                Logger logger = Logger.getLogger("SortingController");
                logger.log(Level.SEVERE, String.valueOf(radioId));
            }
        };
        root.findViewById(R.id.radioButton_dialogSortPref_name).setOnClickListener(onClickListenerArg);
        root.findViewById(R.id.radioButton_dialogSortPref_numberOfPages).setOnClickListener(onClickListenerArg);
        root.findViewById(R.id.radioButton_dialogSortPref_yearOfPublication).setOnClickListener(onClickListenerArg);

        return root;
    }*/

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        /*View v = LayoutInflater.from(getContext()).inflate(R.layout.dialog_sort_preferences, null);
        selectArg = v.findViewById(R.id.radioGroup_dialogSortPref_selectArg);
        selectOrder = v.findViewById(R.id.radioGroup_dialogSortPref_selectOrder);*/
        /*View.OnClickListener onClickListenerArg = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioId = selectArg.getCheckedRadioButtonId();
                //Toast.makeText(getContext(), String.valueOf(radioId), Toast.LENGTH_LONG).show();
                Logger logger = Logger.getLogger("SortingController");
                logger.log(Level.SEVERE, String.valueOf(radioId));
            }
        };
        v.findViewById(R.id.radioButton_dialogSortPref_name).setOnClickListener(onClickListenerArg);
        v.findViewById(R.id.radioButton_dialogSortPref_numberOfPages).setOnClickListener(onClickListenerArg);
        v.findViewById(R.id.radioButton_dialogSortPref_yearOfPublication).setOnClickListener(onClickListenerArg);*/
        /*selectArg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

            }
        });*/
        //Switch aSwitch = v.findViewById(R.id.switch1);
        View v = LayoutInflater.from(getContext()).inflate(R.layout.dialog_sort_preferences, null);
        selectArg = v.findViewById(R.id.radioGroup_dialogSortPref_selectArg);
        selectOrder = v.findViewById(R.id.radioGroup_dialogSortPref_selectOrder);
        return new AlertDialog.Builder(getContext())
                .setTitle("Сортировка")
                .setView(v)
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
