package com.poplar.collector;

import java.util.*;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class MyCollector1<T> implements Collector<T, Set<T>, Map<T, T>> {

    @Override
    public Supplier<Set<T>> supplier() {
        System.out.println("supplier invoked");
//        return HashSet::new;
//        return ConcurrentSkipListSet::new;
        return () -> {
            System.out.println("-----------------------");
            return new HashSet<>();
        };
    }

    @Override
    public BiConsumer<Set<T>, T> accumulator() {
        System.out.println("accumulator invoked");
        return (set, i) -> {
            // 在加上CONCURRENT和parallelStream的并行流情况下
            // 第一个线程在执行set.add方法，第二个线程在执行print(set)，导致遍历set集合可能就会出现并发修改集合的异常
            System.out.println("set:" + set + ", " + Thread.currentThread().getName());
            // 如果去掉CONCURRENT，每个线程都有一个自己的容器，因此多个结果有多个中间容器，相互之间互不干扰了。
            set.add(i);
        };
    }

    @Override
    public BinaryOperator<Set<T>> combiner() {
        System.out.println("combiner invoked");
        // 加上CONCURRENT的模式下，使用的结果集是同一个，因此没有必要在执行下面的合并操作
        return (set, item) -> {
            // 在不加上CONCURRENT的模式下，才会进入combiner进行数据集合并
            System.out.println("set1: " + set);
            System.out.println("item: " + item);
            set.addAll(item);
            return set;
        };
    }

    @Override
    public Function<Set<T>, Map<T, T>> finisher() {
        System.out.println("finisher invoked");
        return (i) -> {
            Map<T, T> map = new HashMap<>();
            i.forEach(t -> map.put(t, t));
            return map;
        };
    }

    @Override
    public Set<Characteristics> characteristics() {
        System.out.println("characteristics invoked");
        // 陷阱一: 这里不能使用IDENTITY_FINISH，因为容器类型和返回类型不一致
        // 陷阱二: 如果容器不是能支持并发运行的，比如HashSet，那么设置了 CONCURRENT, 就会出现并发修改该集合导致出现并发修改集合异常
        // Characteristics.CONCURRENT 要求同一个结果容器可以被多个线程所调用, 并且如果一个Collector不是UNORDERED的，他也只能并行的用于"无序"的数据源
        // 不加上CONCURRENT, 还是使用并行流处理的话，就会产生多个中间集合，最后在依次合并，因此就没有并发修改同一个结果容器的问题
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.UNORDERED));
    }


    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello", "hello", "world", "beautiful cat", "severn cat", "a", "b", "c", "d",
                "E", "f", "G", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "S", "t", "u", "v", "W", "x", "y", "z");
        List<String> list2 = new ArrayList<>(100);
        for (int i = 0; i < 100; i++) {
            list2.add("i:" + i);
        }
        Map<String, String> map = list2.parallelStream().collect(new MyCollector1<>());
        System.out.println(map);

    }
}
