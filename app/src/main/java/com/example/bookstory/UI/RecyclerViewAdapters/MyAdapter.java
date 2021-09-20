package com.example.bookstory.UI.RecyclerViewAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookstory.DAO.relations.BookAuthorCrossRef.BookWithAuthors;
import com.example.bookstory.R;
import com.example.bookstory.UI.Fragments.BookListFragmentDirections;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    List<BookWithAuthors> BooksWithAuthors;

    public MyAdapter(Context context, List<BookWithAuthors> books) {
        this.context = context;
        this.BooksWithAuthors = books;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        return new MyViewHolder(
                layoutInflater.inflate(R.layout.book_row, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        BookWithAuthors currentBook = BooksWithAuthors.get(position);
        holder.bookNameTv.setText(currentBook.book.bookName);
        holder.bookWithAuthors = currentBook;
        StringBuilder sb = new StringBuilder();
        sb.append(currentBook.authors.get(0).authorName);
        if (currentBook.authors.size() > 1) {
            sb.append(" and ").append(currentBook.authors.size() - 1).append(" more");
        }
        holder.authorNameTv.setText(sb.toString());
        holder.yearOfPublishingTv.setText(String.valueOf(currentBook.book.yearOfPublication));
    }

    @Override
    public int getItemCount() {
        return BooksWithAuthors.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView bookNameTv, authorNameTv, yearOfPublishingTv;
        BookWithAuthors bookWithAuthors;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            bookNameTv = itemView.findViewById(R.id.bookName);
            authorNameTv = itemView.findViewById(R.id.authorName);
            yearOfPublishingTv = itemView.findViewById(R.id.yearOfPublishing);
            itemView.setOnClickListener(v -> {
                NavController navController = Navigation.findNavController(v);

                BookListFragmentDirections.ActionBookListFragmentToBookDescriptionFragment actionNav =
                        BookListFragmentDirections.actionBookListFragmentToBookDescriptionFragment(
                                bookWithAuthors.book
                        );
                navController.navigate(actionNav);
            });
        }
    }
}
