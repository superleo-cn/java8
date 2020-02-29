package com.poplar.stream;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created BY poplar ON 2019/11/23
 * 流的操作是类似于流水线的运作模式的，对于进入流中的每一个元素都会执行一遍相同的操作
 */
public class StreamTest9 {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("Hi", "Hello", "你好");
        List<String> list2 = Arrays.asList("张三", "李四", "王五");
        list.stream().flatMap(i -> list2.stream().map(j -> i + " " + j)).forEach(System.out::println);
    }
}
