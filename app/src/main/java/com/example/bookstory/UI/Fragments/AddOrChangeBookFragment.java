package com.example.bookstory.UI.Fragments;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.bookstory.DAO.Author;
import com.example.bookstory.DOMAIN.DBController;
import com.example.bookstory.R;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;
import com.google.android.material.chip.ChipGroup;

public class AddOrChangeBookFragment extends Fragment {

    private View root;
    EditText bookName;
    AutoCompleteTextView authorSelectionActv, characterSelectionActv;
    ChipGroup authorSelectionCg, characterSelectionCg;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_add_or_change_book, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DBController dbController = new DBController(getContext());

        bookName = root.findViewById(R.id.editText_addOrChangeBook_bookName);
        authorSelectionActv = root.findViewById(R.id.autoCompleteTextView_addOrChangeBook_authorSelection);
        characterSelectionActv = root.findViewById(R.id.autoCompleteTextView_addOrChangeBook_characterSelection);
        authorSelectionCg = root.findViewById(R.id.chipGroup_addOrChangeBook_authorSelection);
        characterSelectionCg = root.findViewById(R.id.chipGroup_addOrChangeBook_characterSelection);

        authorSelectionActv.setAdapter(new ArrayAdapter<String>(
                getContext(), android.R.layout.simple_list_item_1,
                dbController.getAuthorNames()));

        authorSelectionActv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView tv = view.findViewById(android.R.id.text1);
                String stringAuthorName = tv.getText().toString();

                //TODO: check if this author already exists
                Chip chip = new Chip(root.getContext());
                ChipDrawable chipDrawable = ChipDrawable.createFromAttributes(
                        root.getContext(),
                        null,
                        0,
                        R.style.Widget_MaterialComponents_Chip_Entry);
                chip.setChipDrawable(chipDrawable);
                chip.setCheckable(false);
                chip.setClickable(false);
                chip.setChipStartPadding(3f);
                chip.setPadding(60, 10, 60, 10);
                chip.setText(stringAuthorName);
                chip.setOnCloseIconClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        authorSelectionCg.removeView(v);
                    }
                });
                authorSelectionCg.addView(chip);
                authorSelectionActv.setText("");
            }
        });

        authorSelectionActv.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                String stringNewAuthorName = v.getText().toString();

                if (!dbController.getAuthors().contains(new Author(stringNewAuthorName))) {
                    dbController.insertAuthor(new Author(stringNewAuthorName));
                }

                v.setText("");
                return false;
            }
        });

        characterSelectionActv.setAdapter(new ArrayAdapter<String>(
                getContext(), android.R.layout.simple_list_item_1,
                dbController.getCharacterNames()));
        characterSelectionActv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView tv = view.findViewById(android.R.id.text1);
                String stringCharacterName = tv.getText().toString();

                //TODO: check if this character already exists
                Chip chip = new Chip(root.getContext());
                ChipDrawable chipDrawable = ChipDrawable.createFromAttributes(
                        root.getContext(),
                        null,
                        0,
                        R.style.Widget_MaterialComponents_Chip_Entry);
                chip.setChipDrawable(chipDrawable);
                chip.setCheckable(false);
                chip.setClickable(false);
                chip.setChipStartPadding(3f);
                chip.setPadding(60, 10, 60, 10);
                chip.setText(stringCharacterName);
                chip.setOnCloseIconClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        characterSelectionCg.removeView(v);
                    }
                });
                characterSelectionCg.addView(chip);
                authorSelectionActv.setText("");
            }
        });
    }
}