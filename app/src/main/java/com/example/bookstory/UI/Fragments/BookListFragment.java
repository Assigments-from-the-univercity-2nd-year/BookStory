package com.example.bookstory.UI.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bookstory.DAO.Author;
import com.example.bookstory.DAO.Book;
import com.example.bookstory.DAO.relations.BookAuthorCrossRef.BookWithAuthors;
import com.example.bookstory.R;
import com.example.bookstory.UI.RecyclerViewAdapters.MyAdapter;

import java.util.ArrayList;
import java.util.List;

public class BookListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_book_list, container, false);
        RecyclerView recyclerView = root.findViewById(R.id.recyclerView);
        //test starts here//TODO
        /*
        List<Book> list = new ArrayList<>();
        list.add(new Book("Harry POtter", 319, 1997, "The famous series"));
        list.add(new Book("Harry POtter 1", 425, 1997, "The famous series"));
        list.add(new Book("Harry POtter 2", 777, 1998, "The famous series"));
        list.add(new Book("Harry POtter 3", 192, 1998, "The famous series"));
        list.add(new Book("Harry POtter 4", 325, 1999, "The famous series"));
        list.add(new Book("Harry POtter 4", 325, 1999, "The famous series"));
        list.add(new Book("Harry POtter 4", 325, 1999, "The famous series"));
        list.add(new Book("Harry POtter 4", 325, 1999, "The famous series"));
        list.add(new Book("Harry POtter 4", 325, 1999, "The famous series"));
        list.add(new Book("Harry POtter 4", 325, 1999, "The famous series"));
        list.add(new Book("Harry POtter 4", 325, 1999, "The famous series"));
        list.add(new Book("Harry POtter 4", 325, 1999, "The famous series"));
        list.add(new Book("Harry POtter 4", 325, 1999, "The famous series"));
        list.add(new Book("Harry POtter 4", 325, 1999, "The famous series"));
        list.add(new Book("Harry POtter 4", 325, 1999, "The famous series"));
        list.add(new Book("Harry POtter 4", 325, 1999, "The famous series"));
        list.add(new Book("Harry POtter 4", 325, 1999, "The famous series"));
        */
        List<BookWithAuthors> list = new ArrayList<>();
        List<Author> authorList = new ArrayList<>();
        authorList.add(new Author("J.K. Rouling"));
        list.add(
                new BookWithAuthors(
                        new Book("Harry POtter",
                                319,
                                1997,
                                "The famous series"),
                        authorList)
        );
        List<Author> authorList2 = new ArrayList<>(authorList);
        authorList2.add(new Author("J.K. Rouling-2"));
        authorList2.add(new Author("J.K. Rouling-2"));
        list.add(
                new BookWithAuthors(
                        new Book("Harry POtter",
                                319,
                                1997,
                                "The famous series"),
                        authorList2)
        );
        //test stops here//TODO

        recyclerView.setAdapter(new MyAdapter(getContext(), list));//TODO replase this ArrayList with real values
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        return root;
    }
}