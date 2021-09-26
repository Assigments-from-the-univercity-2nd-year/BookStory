package com.example.bookstory.DOMAIN;

import com.example.bookstory.DOMAIN.Sortables.Sortable;
import com.example.bookstory.DOMAIN.enums.Criterion;
import com.example.bookstory.DOMAIN.enums.Order;
import com.example.bookstory.DOMAIN.enums.SortingAlgorithm;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SortingController {
    public static <T> void sort(
            List<T> list,
            Comparator<? super T> comparator,
            Class<? extends Sortable> sortingAlgoClass) {
        try {
            Constructor<? extends Sortable> constructor = sortingAlgoClass.getConstructor(sortingAlgoClass);
            Sortable sortable = constructor.newInstance();
            sortable.sort(list, comparator);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            Logger logger = Logger.getLogger("SortingController");
            logger.log(Level.SEVERE, "The " + sortingAlgoClass.getCanonicalName() +
                    " should have a public default constructor with no parameters.");
        } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
            Logger logger = Logger.getLogger("SortingController");
            logger.log(Level.SEVERE, "The " + sortingAlgoClass.getCanonicalName() +
                    " should have a public de3fault constructor with no parameters.");
        }
    }
}
