package com.example.bookstory.DOMAIN.Sortables;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MergeSort implements Sortable{
    @Override
    public <T> void sort(List<T> list, Comparator<? super T> c) {
        mergeSort(list, list.size(), c);
    }

    private <T> void mergeSort(List<T> list, int n, Comparator<? super T> c) {
        if (n < 2) {
            return;
        }
        int mid = n / 2;
        List<T> l = new ArrayList<>(mid);
        List<T> r = new ArrayList<>(n-mid);

        for (int i = 0; i < mid; i++) {
            l.set(i, list.get(i));
        }
        for (int i = mid; i < n; i++) {
            r.set(i-mid, list.get(i));
        }
        mergeSort(l, mid, c);
        mergeSort(r, n - mid, c);

        merge(list, l, r, mid, n - mid, c);
    }

    public <T> void merge(List<T> list, List<T> l, List<T> r, int left, int right, Comparator<? super T> c) {

        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (c.compare(l.get(i), r.get(j)) <= 0) {
                list.set(k++, l.get(i++));
            }
            else {
                list.set(k++, r.get(j++));
            }
        }
        while (i < left) {
            list.set(k++, l.get(i++));
        }
        while (j < right) {
            list.set(k++, r.get(j++));
        }
    }
}
