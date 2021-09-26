package com.example.bookstory.DAO;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Book implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    public long bookId;

    public String bookName;
    public int numberOfPages;
    public int yearOfPublication;
    public String annotation;

    public Book(String bookName, int numberOfPages, int yearOfPublication, String annotation) {
        this.bookName = bookName;
        this.numberOfPages = numberOfPages;
        this.yearOfPublication = yearOfPublication;
        this.annotation = annotation;
    }

    protected Book(Parcel in) {
        bookId = in.readLong();
        bookName = in.readString();
        numberOfPages = in.readInt();
        yearOfPublication = in.readInt();
        annotation = in.readString();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(bookId);
        dest.writeString(bookName);
        dest.writeInt(numberOfPages);
        dest.writeInt(yearOfPublication);
        dest.writeString(annotation);
    }
}
