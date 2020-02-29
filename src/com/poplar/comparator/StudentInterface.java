package com.poplar.comparator;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public interface StudentInterface {

    default void test() {
        System.out.println("Student Interface");
    }

    static void myTest() {
        System.out.println("My interface");
    }
}