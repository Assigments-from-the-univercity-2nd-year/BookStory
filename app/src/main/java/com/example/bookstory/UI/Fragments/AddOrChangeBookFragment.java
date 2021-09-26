package com.example.bookstory.UI.Fragments;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.bookstory.DAO.Author;
import com.example.bookstory.DAO.Character;
import com.example.bookstory.DOMAIN.DBController;
import com.example.bookstory.R;
import com.example.bookstory.UI.elements.CharacterPseudonymsDialogFragment;
import com.example.bookstory.UI.elements.CustomChip;
import com.example.bookstory.UI.elements.CustomChipGroup;

import java.util.ArrayList;
import java.util.List;

public class AddOrChangeBookFragment extends Fragment
        implements CharacterPseudonymsDialogFragment.CharacterPseudonymsDialogListener {

    private View root;
    private EditText bookNameEt;
    private AutoCompleteTextView authorSelectionActv, characterSelectionActv;
    private CustomChipGroup authorSelectionCg, characterSelectionCg;
    private DBController dbController;
    String stringNewCharacterName = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_add_or_change_book, container, false);
        setHasOptionsMenu(true);
        return root;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.apply_menu, menu);
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

                if (!dbController.getAuthors().contains(new Author(stringNewAuthorName))
                        && !stringNewAuthorName.equals("") && !stringNewAuthorName.equals(" ")) {
                    dbController.insertAuthor(new Author(stringNewAuthorName));
                }

                if (!isAuthorInChipGroup(stringNewAuthorName, authorSelectionCg)
                        && !stringNewAuthorName.equals("") && !stringNewAuthorName.equals(" ")) {
                    authorSelectionCg.addView(new CustomChip(authorSelectionCg, stringNewAuthorName));
                }

                v.setText("");
                //updating the list of choices
                initSelectionActv(authorSelectionActv, dbController.getAuthorNames());
                return false;
            }
        });

        initSelectionActv(characterSelectionActv, dbController.getCharacterNames());
        setOnItemClickListenerForSelectionActv(characterSelectionActv, characterSelectionCg);
        characterSelectionActv.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                stringNewCharacterName = v.getText().toString();

                if (!dbController.getCharacters().contains(new Character(stringNewCharacterName, ""))
                        && !stringNewCharacterName.equals("") && !stringNewCharacterName.equals(" ")) {
                    CharacterPseudonymsDialogFragment dialogFragment = new CharacterPseudonymsDialogFragment();
                    dialogFragment.show(getChildFragmentManager(), CharacterPseudonymsDialogFragment.TAG);
                }

                if (!isAuthorInChipGroup(stringNewCharacterName, characterSelectionCg)
                        && !stringNewCharacterName.equals("") && !stringNewCharacterName.equals(" ")) {
                    characterSelectionCg.addView(new CustomChip(characterSelectionCg, stringNewCharacterName));
                }

                v.setText("");
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemMenu_applyMenu_check:
                applyChanges();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Check whether there is a Author with such name in a CustomChipGroup
     *
     * @param authorName name of an Author
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
        bookNameEt = root.findViewById(R.id.editText_addOrChangeBook_bookName);
        authorSelectionActv = root.findViewById(R.id.autoCompleteTextView_addOrChangeBook_authorSelection);
        characterSelectionActv = root.findViewById(R.id.autoCompleteTextView_addOrChangeBook_characterSelection);
        authorSelectionCg = root.findViewById(R.id.chipGroup_addOrChangeBook_authorSelection);
        characterSelectionCg = root.findViewById(R.id.chipGroup_addOrChangeBook_characterSelection);
    }

    @Override
    public void applyCharacterPseudonyms(String characterPseudonyms) {
        dbController.insertCharacter(new Character(stringNewCharacterName, characterPseudonyms));

        if (!isAuthorInChipGroup(stringNewCharacterName, characterSelectionCg)) {
            characterSelectionCg.addView(new CustomChip(characterSelectionCg, stringNewCharacterName));
        }
        stringNewCharacterName = null;

        //updating the list in autoComplete
        initSelectionActv(characterSelectionActv, dbController.getCharacterNames());
    }

    private void applyChanges() {
        String bookName = bookNameEt.getText().toString();
        List<Author> authors = getAuthors();
        List<Character> characters = getCharacters();
    }

    @NonNull
    private List<Author> getAuthors() {
        List<Author> authors = new ArrayList<>();
        for (CustomChip customChip : authorSelectionCg.getChips()) {
            authors.add(dbController.getAuthorByName(customChip.getCustomChipText()));
        }
        return authors;
    }

    @NonNull
    private List<Character> getCharacters() {
        List<Character> characters = new ArrayList<>();
        for (CustomChip customChip : characterSelectionCg.getChips()) {
            characters.add(dbController.getCharacterByName(customChip.getCustomChipText()));
        }
        return characters;
    }
}
