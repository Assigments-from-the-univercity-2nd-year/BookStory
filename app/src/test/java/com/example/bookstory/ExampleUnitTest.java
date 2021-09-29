package com.example.bookstory;

import com.example.bookstory.DAO.Author;
import com.example.bookstory.DAO.Book;
import com.example.bookstory.DAO.relations.BookAuthorCrossRef.BookWithAuthors;
import com.example.bookstory.DOMAIN.DBController;
import com.example.bookstory.DOMAIN.Sortables.BubbleSort;
import com.example.bookstory.DOMAIN.Sortables.InsertionSort;
import com.example.bookstory.DOMAIN.Sortables.MergeSort;
import com.example.bookstory.DOMAIN.Sortables.QuickSort;
import com.example.bookstory.DOMAIN.Sortables.SelectionSort;
import com.example.bookstory.DOMAIN.SortingController;
import com.example.bookstory.DOMAIN.enums.Criterion;
import com.example.bookstory.DOMAIN.enums.Order;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    private static List<BookWithAuthors> list = new ArrayList<>();
    private static List<BookWithAuthors> sortedlist;

    @BeforeAll
    public static void init() {
        list.clear();

        List<Author> authorList = new ArrayList<>();
        authorList.add(new Author("author 1"));
        list.add(new BookWithAuthors(
                new Book("name 1", 135, 1997, ""),
                authorList
        ));
        list.add(new BookWithAuthors(
                new Book("name 2", 178, 1994, "xzxcv"),
                authorList
        ));
        list.add(new BookWithAuthors(
                new Book("name 3", 1255, 1954, "zxcvsag"),
                authorList
        ));
        list.add(new BookWithAuthors(
                new Book("name 4", 14, 1937, "xcf"),
                authorList
        ));
        list.add(new BookWithAuthors(
                new Book("name 5", 185, 1455, "asdfsefwssef"),
                authorList
        ));
        list.add(new BookWithAuthors(
                new Book("name 6", 145, 1999, "a"),
                authorList
        ));

        sortedlist = new ArrayList<>(list);
        sortedlist.sort(getComparator());
    }

    @Test
    public void BubbleSort_isCorrect() {
        BubbleSort bubbleSort = new BubbleSort();
        List<BookWithAuthors> cur = new ArrayList<>(list);
        bubbleSort.sort(cur, getComparator());
        Assertions.assertEquals(cur, sortedlist);
    }

    @Test
    public void InsertionSort_isCorrect() {
        InsertionSort insertionSort = new InsertionSort();
        List<BookWithAuthors> cur = new ArrayList<>(list);
        insertionSort.sort(cur, getComparator());
        Assertions.assertEquals(cur, sortedlist);
    }

    @Test
    public void MergeSort_isCorrect() {
        MergeSort mergeSort = new MergeSort();
        List<BookWithAuthors> cur = new ArrayList<>(list);
        mergeSort.sort(cur, getComparator());
        Assertions.assertEquals(cur, sortedlist);
    }

    @Test
    public void QuickSort_isCorrect() {
        QuickSort quickSort = new QuickSort();
        List<BookWithAuthors> cur = new ArrayList<>(list);
        quickSort.sort(cur, getComparator());
        Assertions.assertEquals(cur, sortedlist);
    }

    @Test
    public void SelectionSort_isCorrect() {
        SelectionSort selectionSort = new SelectionSort();
        List<BookWithAuthors> cur = new ArrayList<>(list);
        selectionSort.sort(cur, getComparator());
        Assertions.assertEquals(cur, sortedlist);
    }

    private static Comparator<? super BookWithAuthors> getComparator() {
        return SortingController.getComparatorForBookWithAuthors(
                Criterion.NAME_OF_TITLE, Order.DESCENDING_ORDER, false
        );
    }
}