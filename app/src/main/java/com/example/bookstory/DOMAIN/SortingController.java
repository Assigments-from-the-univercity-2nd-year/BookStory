package com.example.bookstory.DOMAIN;

import com.example.bookstory.DOMAIN.enums.Criterion;
import com.example.bookstory.DOMAIN.enums.Order;
import com.example.bookstory.DOMAIN.enums.SortingAlgorithm;

import java.util.Comparator;
import java.util.List;

public class SortingController {
    public static <T> void sort(
            List<T> list,
            Comparator<? super T> comparator,
            SortingAlgorithm sortingAlgorithm,
            Order order,
            Criterion... criteria) {
        //TODO the realization
    }
}
