package com.example.bookstory.UI.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookstory.DOMAIN.DBController;
import com.example.bookstory.R;
import com.example.bookstory.UI.RecyclerViewAdapters.MyAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class BookListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_book_list, container, false);
        RecyclerView recyclerView = root.findViewById(R.id.recyclerView_bookList);
        FloatingActionButton floatingActionButton = root.findViewById(R.id.floatingActionButton_bookList);
        floatingActionButton.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);
            BookListFragmentDirections.ActionBookListFragmentToAddBookFragment action =
                    BookListFragmentDirections.actionBookListFragmentToAddBookFragment();
            navController.navigate(action);
        });

        DBController dbController = new DBController(getContext());
        //MOCK TEST
        /*Book book1 = new Book("Harry Potter and the Philosopher's Stone",
                319,
                1997,
                "The first title in a famous series");
        Book book2 = new Book("Harry Potter and the Chamber of Secrets",
                251,
                1998,
                "The second title in a famous series");
        Author author = new Author("J. K. Rowling");
        List<Author> list1 = new ArrayList<>();
        list1.add(author);
        List<Author> list2 = new ArrayList<>();
        list1.add(author);

        dbController.insertBook(book1);
        dbController.insertBook(book2);
        dbController.insertAuthor(author);
        dbController.insertBookAuthorCrossRef(new BookAuthorCrossRef(1, author.authorName));
        dbController.insertBookAuthorCrossRef(new BookAuthorCrossRef(2, author.authorName));*/
        recyclerView.setAdapter(new MyAdapter(getContext(), dbController.getBooksWithAuthors()));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        return root;
    }
}