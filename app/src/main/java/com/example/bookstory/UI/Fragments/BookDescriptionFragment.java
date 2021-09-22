package com.example.bookstory.UI.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bookstory.R;


public class BookDescriptionFragment extends Fragment {

    BookDescriptionFragmentArgs args;
    View root;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_book_description, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        args = BookDescriptionFragmentArgs.fromBundle(getArguments());

        TextView bookName = root.findViewById(R.id.textView_bookDescription_bookName);
        //TODO: get authors
        TextView bookAuthor = root.findViewById(R.id.authorName);
        TextView bookYearOfPublication = root.findViewById(R.id.textView_bookDescription_yearOfPublication);
        TextView bookNumberOfPages = root.findViewById(R.id.textView_bookDescription_numberOfPages);

        bookName.setText(args.getBook().bookName);
        bookYearOfPublication.setText(String.valueOf(args.getBook().yearOfPublication));
        bookNumberOfPages.setText(String.valueOf(args.getBook().numberOfPages));
    }
}