package com.poplar.comparator;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

/*
方法引用四种方式:

1. 类名::静态方法名
2. 引用名(对象名)::实例方法名
3. 类名::实例方法名 -- 注意:这种情况参数列表中的第一个参数作为方法的的调用者，后面的参数就是该执行方法的参数
4. 构造方法引用 -- 类名::new


 */
public class StudentTest {

    public String getString(Supplier<String> supplier) {
        return supplier.get() + " world";
    }

    public String getString2(String str, Function<String, String> fun) {
        return fun.apply(str);
    }

    public String getDemoClass(Supplier<DemoClass> supplier) {
        return supplier.get().toString();
    }

    public static void main(String[] args) {
        Student student = new Student("leo", 89);
        Student student2 = new Student("jobs", 18);
        Student student3 = new Student("gates", 86);
        List<Student> list = Arrays.asList(student, student2, student3);

        list.sort(Student::compareStudentByScore);
        list.forEach(s -> System.out.println(s.getScore() + ": " + s.getName()));

        StudentCompareDemo demo = new StudentCompareDemo();
        list.sort(demo::compare);
        list.forEach(s -> System.out.println(s.getScore() + ": " + s.getName()));

        list.sort(Student::compareStudentByName);
        list.forEach(s -> System.out.println(s.getScore() + ": " + s.getName()));


        List<String> cities = Arrays.asList("beijing", "shangshai", "hongkong", "shengzhen");
        Collections.sort(cities, (c1, c2) -> c1.compareToIgnoreCase(c2));
        // 使用第三种方式，就是参数列表的第一个参数(c1)作为方法(compareToIgnoreCase)的调用者
        // 后面的参数就是方法参数，切记
        Collections.sort(cities, String::compareToIgnoreCase);

        StudentTest test = new StudentTest();
        System.out.println(test.getString(() -> "Hello"));
        System.out.println(test.getString(String::new));
        System.out.println(test.getString2("Hello", (str) -> str + new DemoClass()));

        System.out.println(test.getDemoClass(DemoClass::new));

        StudentInterface.myTest();

        StudentInterface a = new StudentInterface() {};

        a.test();

    }

    static class DemoClass {

        String inner = "逢考必过";

        public DemoClass() {

        }

        @Override
        public String toString() {
            return "DemoClass{" +
                    "inner='" + inner + '\'' +
                    '}';
        }
    }

}

