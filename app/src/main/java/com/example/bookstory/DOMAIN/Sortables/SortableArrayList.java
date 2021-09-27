package com.example.bookstory.DOMAIN.Sortables;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SortableArrayList implements Sortable {

    @Override
    public <T> void sort(List<T> list, Comparator<? super T> c) {
        /*ArrayList<T> arrayList = new ArrayList<>(list);
        arrayList.sort(c);*/
        list.sort(c);
    }
}
