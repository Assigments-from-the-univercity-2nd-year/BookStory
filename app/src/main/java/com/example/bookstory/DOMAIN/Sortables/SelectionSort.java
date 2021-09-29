package com.example.bookstory.DOMAIN.Sortables;

import java.util.Comparator;
import java.util.List;

public class SelectionSort implements Sortable{
    @Override
    public <T> void sort(List<T> list, Comparator<? super T> c) {
        for (int i = 0; i < list.size() - 1; i++)
        {
            int index = i;
            for (int j = i + 1; j < list.size(); j++){
                if (c.compare(list.get(j), list.get(index)) < 0){
                    index = j;
                }
            }
            T temp = list.get(index);
            list.set(index, list.get(i));
            list.set(i, temp);
        }
    }
}
