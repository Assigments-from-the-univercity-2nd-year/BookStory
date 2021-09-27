package com.example.bookstory.UI.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookstory.DAO.relations.BookAuthorCrossRef.BookWithAuthors;
import com.example.bookstory.DOMAIN.DBController;
import com.example.bookstory.DOMAIN.Sortables.SortableArrayList;
import com.example.bookstory.DOMAIN.SortingController;
import com.example.bookstory.DOMAIN.enums.Criterion;
import com.example.bookstory.DOMAIN.enums.Order;
import com.example.bookstory.R;
import com.example.bookstory.UI.RecyclerViewAdapters.BookList;
import com.example.bookstory.UI.elements.SortPreferencesDialogFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class BookListFragment extends Fragment implements SortPreferencesDialogFragment.SortPreferencesDialogListener {

    List<BookWithAuthors> bookWithAuthorsList;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_book_list, container, false);
        setHasOptionsMenu(true);

        recyclerView = root.findViewById(R.id.recyclerView_bookList);
        FloatingActionButton floatingActionButton = root.findViewById(R.id.floatingActionButton_bookList);
        floatingActionButton.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);
            BookListFragmentDirections.ActionBookListFragmentToAddBookFragment action =
                    BookListFragmentDirections.actionBookListFragmentToAddBookFragment(null);
            navController.navigate(action);
        });

        DBController dbController = new DBController(getContext());
        bookWithAuthorsList = dbController.getBooksWithAuthors();
        recyclerView.setAdapter(new BookList(getContext(), bookWithAuthorsList));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        return root;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.sort_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuItem_sortMenu_sortBy:
                SortPreferencesDialogFragment dialogFragment = new SortPreferencesDialogFragment();
                dialogFragment.show(getChildFragmentManager(), SortPreferencesDialogFragment.TAG);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void applySortPreferences(int argId, int sortById, boolean isInclude) {
        Criterion criterion;
        Order order;

        switch (argId) {
            case R.id.radioButton_dialogSortPref_yearOfPublication:
                criterion = Criterion.DATE_OF_PUBLICATION;
                break;
            case R.id.radioButton_dialogSortPref_numberOfPages:
                criterion = Criterion.NUMBER_OF_PAGES;
                break;
            default:
                criterion = Criterion.NAME_OF_TITLE;
        }

        switch (sortById) {
            case R.id.radioButton_dialogSortPref_ASC:
                order = Order.ASCENDING_ORDER;
                break;
            default:
                order = Order.DESCENDING_ORDER;
        }

        SortingController.sort(
                bookWithAuthorsList,
                SortingController.getComparatorForBookWithAuthors(criterion, order, isInclude),
                SortableArrayList.class);
        recyclerView.setAdapter(new BookList(getContext(), bookWithAuthorsList));
    }
}