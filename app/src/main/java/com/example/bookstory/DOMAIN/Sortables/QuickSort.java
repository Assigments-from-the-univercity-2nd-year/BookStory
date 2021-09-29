package com.example.bookstory.DOMAIN.Sortables;

import java.util.Comparator;
import java.util.List;

public class QuickSort implements Sortable {
    @Override
    public <T> void sort(List<T> list, Comparator<? super T> c) {
        quickSort(list, 0, list.size() - 1, c);
    }

    private <T> void quickSort(List<T> list, int begin, int end, Comparator<? super T> c) {
        if (begin < end) {
            int partitionIndex = partition(list, begin, end, c);

            quickSort(list, begin, partitionIndex - 1, c);
            quickSort(list, partitionIndex + 1, end, c);
        }
    }

    private <T> int partition(List<T> list, int begin, int end, Comparator<? super T> c) {
        T pivot = list.get(end);
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (c.compare(list.get(j), pivot) <= 0) {
                i++;

                T swapTemp = list.get(i);
                list.set(i, list.get(j));
                list.set(j, swapTemp);
            }
        }

        T swapTemp = list.get(i + 1);
        list.set(i + 1, list.get(end));
        list.set(end, swapTemp);

        return i + 1;
    }
}
