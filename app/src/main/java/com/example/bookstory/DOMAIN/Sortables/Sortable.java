package com.example.bookstory.DOMAIN.Sortables;

import java.util.Comparator;
import java.util.List;

public interface Sortable {
    <T> void sort(List<T> list, Comparator<? super T> c);
}
