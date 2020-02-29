package com.poplar.stream;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.stream.Stream;

/**
 * Created BY poplar ON 2019/11/23
 * 流的操作是类似于流水线的运作模式的，对于进入流中的每一个元素都会执行一遍相同的操作
 */
public class StreamTest11 {


    public static void aa(Consumer<Integer> action) {
        action.accept(100);
    }

//    public static void bb(IntConsumer action) {
//        action.accept(100);
//    }

    public static void main(String[] args) {
        Consumer<Integer> aa = (a) -> System.out.println("a:" + a);
        IntConsumer bb = (a) -> System.out.println("b:" + a);
        aa(aa);
        // 下面方式不行，面向对象传递的是对象，会出现类型转换异常
        // Exception in thread "main" java.lang.ClassCastException: com.poplar.stream.StreamTest11$$Lambda$2/2074407503
        // cannot be cast to java.util.function.Consumer
//        aa((Consumer<Integer>) bb);
        // 这样就可以了相当于把lambda表达式 (a) -> {} 给传递了过去，相当于传递的是行为
        aa(bb::accept);
        // 同理下面方式也是可以的，只是多此一举
        aa(aa::accept);


        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        Stream<Integer> s1 = list.stream().map(i -> i * 2);
        Stream<Integer> s2 = s1.map(i -> i / 2);
        Stream<Integer> s3 = s1.map(i -> i * 2);
        System.out.println(s2);
        System.out.println(s3);
    }
}
