package com.poplar.comparator;

import java.util.Comparator;

//public class StudentCompareDemo implements Comparator<Student> {
//
//    @Override
//    public int compare(Student o1, Student o2) {
//        return o1.getName().charAt(0) - o2.getName().charAt(0);
//    }
//}


public class StudentCompareDemo {

    public int compare(Student o1, Student o2) {
        return o1.getName().charAt(0) - o2.getName().charAt(0);
    }
}
