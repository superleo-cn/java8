package com.poplar.stream;

import java.util.Arrays;
import java.util.List;

/**
 * Created BY poplar ON 2019/11/23
 * 流的操作是类似于流水线的运作模式的，对于进入流中的每一个元素都会执行一遍相同的操作
 */
public class StreamTest7 {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello", "hello", "world", "beautiful cat", "severn cat");
        //在没有中间操作和当前操作的流为串行流时或调用Head中的实现
        // list.stream().forEach(System.out::println);
        //而如果有中间操作且为并行流会执行ReferencePipeline中的方法
        //list.stream().map(String::toUpperCase).forEach(System.out::println);

        //Iterable接口中的默认实现方法
        //list.forEach(System.out::println);

        // 这里使用 a == 5, 只会输出一个，因为第一次循环就找到了hello，没有必要往后走
        // 如果使用 a > 5, 前面3个 hello, hello, world 都不满足，当找到beautiful cat 最后就输出了这个
        list.stream().map(s -> {
            System.out.println(s);
            return s.length();
        }).filter(a -> a > 5).findFirst().ifPresent(System.out::println);

        System.out.println("=============");
        list.stream().sorted(String::compareTo).forEach(System.out::println);

        System.out.println("=============");
        List<String> list2= Arrays.asList("hello welcome", "hello world", "nice world", "beautiful world");
        list2.stream().map(s -> s.split(" ")).flatMap(Arrays::stream).distinct().forEach(System.out::println);
    }
}
