package com.example.bookstory.DOMAIN;

import androidx.annotation.NonNull;

import com.example.bookstory.DAO.relations.BookAuthorCrossRef.BookWithAuthors;
import com.example.bookstory.DOMAIN.Sortables.Sortable;
import com.example.bookstory.DOMAIN.enums.Criterion;
import com.example.bookstory.DOMAIN.enums.Order;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SortingController {

    public static Comparator<? super BookWithAuthors> getComparatorForBookWithAuthors(
            Criterion criterion, Order order, boolean isInclude
    ) {
        class BookWithAuthorsComparator implements Comparator {

            @Override
            public int compare(Object o1, Object o2) {
                int result = 0;

                BookWithAuthors b1 = (BookWithAuthors) o1;
                BookWithAuthors b2 = (BookWithAuthors) o2;

                switch (criterion) {
                    case NAME_OF_TITLE:
                        result = b1.book.bookName.compareTo(b2.book.bookName);
                        break;
                    case YEAR_OF_PUBLICATION:
                        result = Integer.compare(b1.book.yearOfPublication, b2.book.yearOfPublication);
                        break;
                    case NUMBER_OF_PAGES:
                        result = Integer.compare(b1.book.numberOfPages, b2.book.numberOfPages);
                }

                if (isInclude && result == 0) {
                    result = Integer.compare(b1.book.annotation.length(), b2.book.annotation.length());
                }

                if (order == Order.DESCENDING_ORDER) {
                    result *= -1;
                }

                return result;
            }

        }

        return new BookWithAuthorsComparator();
    }

    public static <T> void sort(
            List<T> list,
            Comparator<? super T> comparator,
            @NonNull Sortable sortingAlgo) {
        sortingAlgo.sort(list, comparator);
    }
}
