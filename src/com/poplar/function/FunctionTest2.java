package com.poplar.function;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * BY poplar ON 2019/11/17
 * 函数式编程
 * 测试：compose()方法和andThen()方法
 */
public class FunctionTest2 {

    public static void main(String[] args) {
        FunctionTest2 functionTest = new FunctionTest2();
        System.out.println(functionTest.computer1(2, value -> value * 3, value -> value * value));//12
        System.out.println(functionTest.computer2(2, value -> value * 3, value -> value * value));//36


        Function<String, String> f = Function.identity();
        System.out.println(f);
        System.out.println(f.apply("hello world"));

        System.out.println(functionTest.compute3(1, 1, (a, b) -> a + b));
        System.out.println(functionTest.compute3(1, 1, (a, b) -> a - b));
        System.out.println(functionTest.compute3(1, 1, (a, b) -> a * b));
        System.out.println(functionTest.compute3(1, 1, (a, b) -> a / b));

        System.out.println(functionTest.<Long, Long, Long>compute4(1L, 1L, (a, b) -> a + b));
        System.out.println(functionTest.<Float, Float, Float>compute4(0.1f, 0.4f, (a, b) -> a + b));

        System.out.println(functionTest.<Integer, Integer, Integer, Integer>compute5(1, 1, (a, b) -> a + b, (x) -> x * 10));
    }

    public int computer1(int a, Function<Integer, Integer> function1, Function<Integer, Integer> function2) {
        return function1.compose(function2).apply(a);
    }

    public int computer2(int a, Function<Integer, Integer> function1, Function<Integer, Integer> function2) {
        return function1.andThen(function2).apply(a);
    }

    public int compute3(int a, int b, BiFunction<Integer, Integer, Integer> function3) {
        return function3.apply(a, b);
    }

    public <T, U, R> R compute4(T a, U b, BiFunction<T, U, R> function4) {
        return function4.apply(a, b);
    }

    public <T, U, V, R> V compute5(T a, U b, BiFunction<T, U, R> function5, Function<R, V> function) {
        return function5.andThen(function).apply(a, b);
    }
}
