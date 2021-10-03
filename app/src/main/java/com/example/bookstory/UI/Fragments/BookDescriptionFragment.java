package com.example.bookstory.UI.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookstory.DAO.Author;
import com.example.bookstory.DAO.Character;
import com.example.bookstory.DAO.relations.BookAuthorCrossRef.BookWithAuthors;
import com.example.bookstory.DOMAIN.DBController;
import com.example.bookstory.R;
import com.example.bookstory.UI.RecyclerViewAdapters.CharacterList;

import java.util.List;


public class BookDescriptionFragment extends Fragment {

    BookDescriptionFragmentArgs args;
    View root;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_book_description, container, false);
        setHasOptionsMenu(true);
        setOnBackPressed();
        return root;
    }


    private void setOnBackPressed() {
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                NavController navController = Navigation.findNavController(root);
                navController.navigate(BookDescriptionFragmentDirections.actionBookDescriptionFragmentToBookListFragment());
            }
        };

        requireActivity().getOnBackPressedDispatcher().addCallback(callback);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.edit_menu, menu);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        args = BookDescriptionFragmentArgs.fromBundle(getArguments());
        DBController dbController = new DBController(getContext());
        RecyclerView recyclerView = root.findViewById(R.id.recyclerView_bookDescription_characters);
        BookWithAuthors bookWithAuthors = dbController.getBookWithAuthor(args.getBook().bookId);
        List<Character> characters = dbController.getBookWithCharacter(args.getBook().bookId).characters;

        TextView bookName = root.findViewById(R.id.textView_bookDescription_bookName);
        TextView bookAuthor = root.findViewById(R.id.textView_bookDescription_author);
        TextView bookYearOfPublication = root.findViewById(R.id.textView_bookDescription_yearOfPublication);
        TextView bookNumberOfPages = root.findViewById(R.id.textView_bookDescription_numberOfPages);
        TextView noCharactersTextView = root.findViewById(R.id.textView_bookDescription_showIfNoCharacters);

        bookName.setText(args.getBook().bookName);
        bookAuthor.setText(getAuthorsInStringRepresentation(bookWithAuthors.authors));
        bookYearOfPublication.setText(String.valueOf(args.getBook().yearOfPublication));
        bookNumberOfPages.setText(String.valueOf(args.getBook().numberOfPages));

        recyclerView.setAdapter(new CharacterList(getContext(), characters, args.getBook()));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        if (!characters.isEmpty()) {
            noCharactersTextView.setVisibility(View.GONE);
        }
    }

    private String getAuthorsInStringRepresentation(List<Author> authors) {
        StringBuilder sb = new StringBuilder();
        if (authors.isEmpty()) {
            sb.append("Автор не указан");
        } else {
            sb.append(authors.get(0).authorName);
            for (int i = 1; i < authors.size(); i++) {
                sb.append("\n").append(authors.get(i).authorName);
            }
        }
        return sb.toString();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemMenu_editMenu_edit:
                NavController navController = Navigation.findNavController(root);
                BookDescriptionFragmentDirections.ActionBookDescriptionFragmentToAddOrChangeBookFragment2 action =
                        BookDescriptionFragmentDirections.actionBookDescriptionFragmentToAddOrChangeBookFragment2().setBook(args.getBook());
                navController.navigate(action);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}