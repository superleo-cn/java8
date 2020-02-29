package com.poplar.stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created BY poplar ON 2019/11/20
 */
public class StreamTest4 {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello world", "world", "hello world", "hello welcome", "welcome");
        //list.stream().filter(e -> e.length() == 5).findFirst().ifPresent(System.out::println);
        //去重flatMap()把几个数组合并到一个流里面
        list.stream().map(e -> e.split(" ")).flatMap(Arrays::stream).distinct().
                collect(Collectors.toCollection(LinkedHashSet::new)).forEach(System.out::println);
        System.out.println("=========");

        list.stream().map(i -> i.substring(0, 1)).collect(LinkedList::new, LinkedList::add, LinkedList::addAll).forEach(System.out::println);

        System.out.println("=========");

        list.stream().map(String::toUpperCase).collect(Collectors.toCollection(HashSet::new)).forEach(System.out::println);

        System.out.println("=========");

        Stream<Integer> stream = Stream.of(1, 2, 33, 44, 5, 6, 7);
        stream.skip(3).limit(2).forEach(System.out::println);
        System.out.println("=========");
        Stream<List<Integer>> s2 = Stream.of(Arrays.asList(1, 2, 3), Arrays.asList(4, 5, 6));
        s2.flatMap(Collection::stream).map(i -> i + 2).forEach(System.out::println);
        System.out.println("=========");

    }
}
