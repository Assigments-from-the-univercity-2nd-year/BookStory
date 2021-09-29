package com.example.bookstory.DOMAIN.Sortables;

import java.util.Comparator;
import java.util.List;

public class BubbleSort implements Sortable{
    @Override
    public <T> void sort(List<T> list, Comparator<? super T> c) {
        int n = list.size();
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++)
                if (c.compare(list.get(j), list.get(j+1)) > 0) {
                    T temp = list.get(j);
                    list.set(j, list.get(j+1));
                    list.set(j+1, temp);
                }
    }
}
