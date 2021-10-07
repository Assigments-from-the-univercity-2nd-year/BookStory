package com.example.bookstory.DOMAIN.Sortables;

import java.util.Comparator;
import java.util.List;

public class InsertionSort implements Sortable{
    @Override
    public <T> void sort(List<T> list, Comparator<? super T> c) {
        int n = list.size();
        for (int j = 1; j < n; j++) {
            T key = list.get(j);
            int i = j-1;
            while ( (i > -1) && (c.compare(list.get(i), key) > 0) ) {
                list.set(i+1, list.get(i));
                i--;
            }
            list.set(i+1, key);
        }
    }
}
