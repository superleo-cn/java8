package com.poplar.stream;

import java.util.function.Consumer;

/**
 * Created BY poplar ON 2019/11/23
 * 流的操作是类似于流水线的运作模式的，对于进入流中的每一个元素都会执行一遍相同的操作
 */
public class StreamTest12 {


    public static void aa(Consumer<Integer> action) {
        action.accept(100);
    }

//    public static void bb(IntConsumer action) {
//        action.accept(100);
//    }

    public static void main(String[] args) {
    }
}
