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
import com.example.bookstory.DAO.Character;
import com.example.bookstory.DOMAIN.DBController;
import com.example.bookstory.R;
import com.example.bookstory.UI.elements.CustomChip;
import com.example.bookstory.UI.elements.CustomChipGroup;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;
import com.google.android.material.chip.ChipGroup;

import java.util.List;

public class AddOrChangeBookFragment extends Fragment {

    private View root;
    private EditText bookName;
    private AutoCompleteTextView authorSelectionActv, characterSelectionActv;
    private CustomChipGroup authorSelectionCg, characterSelectionCg;
    private DBController dbController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_add_or_change_book, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dbController = new DBController(getContext());
        initAllViewsFromXML();

        initSelectionActv(authorSelectionActv, dbController.getAuthorNames());
        setOnItemClickListenerForSelectionActv(authorSelectionActv, authorSelectionCg);
        authorSelectionActv.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                String stringNewAuthorName = v.getText().toString();

                if (!dbController.getAuthors().contains(new Author(stringNewAuthorName))) {
                    dbController.insertAuthor(new Author(stringNewAuthorName));
                }

                if (!isAuthorInChipGroup(stringNewAuthorName, authorSelectionCg)) {
                    authorSelectionCg.addView(new CustomChip(authorSelectionCg, stringNewAuthorName));
                }

                v.setText("");
                return false;
            }
        });

        initSelectionActv(characterSelectionActv, dbController.getCharacterNames());
        setOnItemClickListenerForSelectionActv(characterSelectionActv, characterSelectionCg);
        characterSelectionActv.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                String stringNewAuthorName = v.getText().toString();

                if (!dbController.getCharacters().contains(new Character(stringNewAuthorName, ""))) {
                    //TODO: show bottomShit for entering pseudonyms of the character
                    dbController.insertCharacter(new Character(stringNewAuthorName, ""));
                }

                if (!isAuthorInChipGroup(stringNewAuthorName, characterSelectionCg)) {
                    characterSelectionCg.addView(new CustomChip(characterSelectionCg, stringNewAuthorName));
                }

                v.setText("");
                return false;
            }
        });
    }

    /**
     * Check whether there is a Author with such name in a CustomChipGroup
     *
     * @param authorName name of an Auhor
     * @param customChipGroup
     * @return
     */
    private boolean isAuthorInChipGroup(String authorName, @NonNull CustomChipGroup customChipGroup) {
        for (CustomChip customChip : customChipGroup.getChips()) {
            if (authorName.equals(customChip.getCustomChipText())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Initialize an AutoCompleteTextView
     *
     * @param selectionActv AutoCompleteTextView that is wanted to be init
     * @param listItems
     */
    private void initSelectionActv(@NonNull AutoCompleteTextView selectionActv, List<String> listItems) {
        selectionActv.setAdapter(new ArrayAdapter<String>(
                getContext(), android.R.layout.simple_list_item_1, listItems
        ));
    }

    private void setOnItemClickListenerForSelectionActv(AutoCompleteTextView selectionActv,
                                                        CustomChipGroup selectionCg) {
        selectionActv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView tv = view.findViewById(android.R.id.text1);
                String stringAuthorName = tv.getText().toString();
                if (!isAuthorInChipGroup(stringAuthorName, selectionCg)) {
                    selectionCg.addView(new CustomChip(selectionCg, stringAuthorName));
                }
                selectionActv.setText("");
            }
        });
    }

    /**
     * This method initialises all XML declared views
     */
    private void initAllViewsFromXML() {
        bookName = root.findViewById(R.id.editText_addOrChangeBook_bookName);
        authorSelectionActv = root.findViewById(R.id.autoCompleteTextView_addOrChangeBook_authorSelection);
        characterSelectionActv = root.findViewById(R.id.autoCompleteTextView_addOrChangeBook_characterSelection);
        authorSelectionCg = root.findViewById(R.id.chipGroup_addOrChangeBook_authorSelection);
        characterSelectionCg = root.findViewById(R.id.chipGroup_addOrChangeBook_characterSelection);
    }

}
