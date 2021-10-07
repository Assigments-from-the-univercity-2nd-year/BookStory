package com.example.bookstory.DOMAIN.Sortables;

import java.util.Comparator;
import java.util.List;

public class DefaultSort implements Sortable {

    @Override
    public <T> void sort(List<T> list, Comparator<? super T> c) {
        list.sort(c);
    }
}
