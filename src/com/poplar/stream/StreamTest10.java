package com.poplar.stream;

import java.util.*;

import static java.util.stream.Collectors.*;

/**
 * Collectors详细demo
 */
public class StreamTest10 {
    public static void main(String[] args) {
        Student student1 = new Student("大明", 18, 90);
        Student student2 = new Student("二明", 19, 90);
        Student student3 = new Student("三明", 20, 70);
        Student student4 = new Student("小明", 17, 80);
        Student student5 = new Student("小明", 17, 100);
        List<Student> list = Arrays.asList(student1, student2, student3, student4, student5);

        System.out.println("count1: " + list.stream().count());
        System.out.println("count2: " + list.stream().collect(counting()));
        System.out.println("-------------------------");

        System.out.print("max:");
        list.stream().min(Comparator.comparing(Student::getScore)).ifPresent(System.out::println);

        System.out.print("min:");
        list.stream().collect(maxBy(Comparator.comparing(Student::getScore))).ifPresent(System.out::println);
        System.out.println("-------------------------");

        System.out.print("average:");
        System.out.println(list.stream().collect(averagingDouble(Student::getScore)));
        System.out.print("max:");
        System.out.println(list.stream().collect(summarizingInt(Student::getAge)));
        System.out.println("-------------------------");
        DoubleSummaryStatistics statistics = list.stream().collect(summarizingDouble(Student::getScore));
        System.out.println(statistics);
        System.out.println("-------------------------");

        System.out.println(list.stream().map(Student::getName).collect(joining(",", "names = [", "]")));
        System.out.println("-------------------------");

        Map<String, Map<Double, List<Student>>> map1 = list.stream().collect(groupingBy(Student::getName, groupingBy(Student::getScore)));
        System.out.println(map1);
        System.out.println("-------------------------");

        Map<Boolean, Map<Boolean, List<Student>>> map2 = list.stream().collect(partitioningBy(s -> s.getAge() > 17, partitioningBy(r -> r.getScore() > 80)));
        System.out.println(map2);
        System.out.println("-------------------------");

        Map<Boolean, Map<String, List<Student>>> map3 = list.stream().collect(partitioningBy(s -> s.getAge() > 17, groupingBy(Student::getName)));
        System.out.println(map3);
        System.out.println("-------------------------");

        Map<String, Long> map4 = list.stream().collect(groupingBy(Student::getName, counting()));
        System.out.println(map4);
        System.out.println("-------------------------");

        Map<String, Student> map5 = list.stream().collect(groupingBy(Student::getName, collectingAndThen(minBy(Comparator.comparingDouble(Student::getScore)), Optional::get)));
        System.out.println(map5);
        System.out.println("-------------------------");

    }
}

