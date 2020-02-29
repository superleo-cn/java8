package com.poplar.comparator;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created BY poplar ON 2019/11/21
 */
public class ComparatorTest1 {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("world", "hello", "beautiful cat", "severn cat");
        list.sort(String::compareTo);
        System.out.println(list);
        Collections.sort(list, (c1, c2) -> c1.length() - c2.length());
        System.out.println("----------------------");
        System.out.println(list);

        System.out.println("----------------------");
        Collections.sort(list, Comparator.comparingInt(String::length));
        System.out.println(list);
        System.out.println("----------------------");
        // 下面的没法编译是因为最外面的Collection.sort调用的第二个参数是reversed 返回的T类型的，它离实际list参数类型比较远，
        // 没法让 Comparator.comparingInt进行类型推断
        // Collections.sort(list, Comparator.comparingInt(s -> s.length()).reversed());
        // 因为reversed 返回的T类型并不能确定就是T类型，也有可能是T类型的父类，这样只能当成Object类型来处理了
        // 这样强制转换一下就好了
        Collections.sort(list, Comparator.comparingInt((String s) -> s.length()).reversed());
        System.out.println(list);
        System.out.println("----------------------");

        list.sort(Comparator.comparingInt(String::length).reversed());
        System.out.println(list);
        System.out.println("----------------------");

//        Collections.sort(list, Comparator.comparingInt(String::length).thenComparing((s1, s2) -> s1.charAt(0) - s2.charAt(0)));
        Collections.sort(list, Comparator.comparingInt(String::length).thenComparing(String.CASE_INSENSITIVE_ORDER));
        System.out.println(list);
        System.out.println("----------------------");

        // 注意当只有当第一个Comparator的比较是相等的情况下，才会使用thenComparing第二个比较
        Collections.sort(list, Comparator.comparingInt(String::length).reversed()
                .thenComparing(Comparator.comparing(String::toLowerCase, Comparator.reverseOrder())));
        // 所以 beautiful cat, severn cat 会一直在前面
        System.out.println(list);
        System.out.println("----------------------");

        // 因此第一次比较选出后
        Collections.sort(list, Comparator.comparingInt(String::length).reversed()
                // 第二个在第一次相等的情况下反转
                .thenComparing(Comparator.comparing(String::toLowerCase, Comparator.reverseOrder())).
                        // 第三个发现已经满足了，就不会在执行了
                        thenComparing(Comparator.reverseOrder()));

        System.out.println("----------------------");
        System.out.println(list);
    }
}
