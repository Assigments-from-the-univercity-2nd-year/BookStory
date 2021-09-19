package com.example.bookstory.UI.RecyclerViewAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookstory.DAO.Book;
import com.example.bookstory.R;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    List<Book> books;

    public MyAdapter(Context context, List<Book> books) {
        this.context = context;
        this.books = books;
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
        Book currentBook = books.get(position);
        holder.bookNameTv.setText(currentBook.bookName);
        //holder.authorNameTv.setText("");
        holder.yearOfPublishingTv.setText(currentBook.yearOfPublication);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView bookNameTv, authorNameTv, yearOfPublishingTv;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            bookNameTv = itemView.findViewById(R.id.bookName);
            authorNameTv = itemView.findViewById(R.id.authorName);
            yearOfPublishingTv = itemView.findViewById(R.id.yearOfPublishing);
        }
    }
}
