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

import com.example.bookstory.DOMAIN.enums.Criterion;
import com.example.bookstory.DOMAIN.enums.Order;
import com.example.bookstory.R;

public class SortPreferencesDialogFragment extends DialogFragment {

    public static String TAG = "SortPreferencesDialogHost";
    private SortPreferencesDialogListener dialogListener;
    RadioGroup selectArg, selectOrder;
    Switch aSwitch;
    View root;
    private Criterion defaultCriterion;
    private Order defaultOrder;
    private boolean defaultIsInclude;

    public SortPreferencesDialogFragment(Criterion defaultCriterion, Order defaultOrder, boolean defaultIsInclude) {
        this.defaultCriterion = defaultCriterion;
        this.defaultOrder = defaultOrder;
        this.defaultIsInclude = defaultIsInclude;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        init();
        return new AlertDialog.Builder(getContext())
                .setTitle("Сортировка")
                .setView(root)
                .setPositiveButton("Применить",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Criterion criterion;
                                Order order;

                                switch (selectArg.getCheckedRadioButtonId()) {
                                    case R.id.radioButton_dialogSortPref_yearOfPublication:
                                        criterion = Criterion.YEAR_OF_PUBLICATION;
                                        break;
                                    case R.id.radioButton_dialogSortPref_numberOfPages:
                                        criterion = Criterion.NUMBER_OF_PAGES;
                                        break;
                                    default:
                                        criterion = Criterion.NAME_OF_TITLE;
                                }

                                switch (selectOrder.getCheckedRadioButtonId()) {
                                    case R.id.radioButton_dialogSortPref_ASC:
                                        order = Order.ASCENDING_ORDER;
                                        break;
                                    default:
                                        order = Order.DESCENDING_ORDER;
                                }

                                dialogListener.applySortPreferences(criterion, order, aSwitch.isChecked());
                            }
                        }).create();
    }

    private void init() {
        root = LayoutInflater.from(getContext()).inflate(R.layout.dialog_sort_preferences, null);
        aSwitch = root.findViewById(R.id.switch1);
        selectArg = root.findViewById(R.id.radioGroup_dialogSortPref_selectArg);
        selectOrder = root.findViewById(R.id.radioGroup_dialogSortPref_selectOrder);

        aSwitch.setChecked(defaultIsInclude);
        switch (defaultCriterion) {
            case NUMBER_OF_PAGES:
                selectArg.check(R.id.radioButton_dialogSortPref_numberOfPages);
                break;
            case YEAR_OF_PUBLICATION:
                selectArg.check(R.id.radioButton_dialogSortPref_yearOfPublication);
                break;
            default:
                selectArg.check(R.id.radioButton_dialogSortPref_name);
        }
        switch (defaultOrder) {
            case ASCENDING_ORDER:
                selectOrder.check(R.id.radioButton_dialogSortPref_ASC);
                break;
            default:
                selectOrder.check(R.id.radioButton_dialogSortPref_DESC);
        }
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

    /**
     * public interface tha allows the communication between
     * this fragment and parent fragment for this fragment
     */
    public interface SortPreferencesDialogListener {
        void applySortPreferences(Criterion criterion, Order order, boolean isInclude);
    }
}
