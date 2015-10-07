/**
 * Copyright (c) 2011-2015 Zauber S.A. -- All rights reserved
 */
package com.flowics.learning.java8.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.Test;

/**
 * Class to show the parralelization. Is not a test as concurrent testing is not simple :-S
 *
 *
 * @author Marcelo
 * @since Aug 6, 2015
 */
public class StreamParallelTest {

    @Test
    public final void ordering_test() {
        final Integer[] intArray = { 1, 2, 3, 4, 5, 6, 7, 8 };
        final List<Integer> listOfIntegers = new ArrayList<>(Arrays.asList(intArray));

        System.out.println("listOfIntegers:");
        listOfIntegers.stream().forEach(e -> System.out.print(e + " "));
        System.out.println("");

        System.out.println("listOfIntegers sorted in reverse order:");
        final Comparator<Integer> normal = Integer::compare;
        Collections.sort(listOfIntegers, normal.reversed());
        listOfIntegers.stream().forEach(e -> System.out.print(e + " "));
        System.out.println("");
        // parallel would break the order
        System.out.println("Parallel stream");
        listOfIntegers.parallelStream().forEach(e -> System.out.print(e + " "));
        System.out.println("");

        System.out.println("Another parallel stream:");
        listOfIntegers.parallelStream().forEach(e -> System.out.print(e + " "));
        System.out.println("");

        // force the order break the parallel
        System.out.println("With forEachOrdered:");
        listOfIntegers.parallelStream().forEachOrdered(e -> System.out.print(e + " "));
        System.out.println("");
    }
}
