package com.poplar.comparator;

public class Student {

    private String name;

    private int score;


    public Student(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public static int compareStudentByScore(Student s1, Student s2) {
        return s1.score - s2.score;
    }

    public int compareStudentByName(Student s2) {
        return this.name.charAt(0) - s2.name.charAt(0);
    }
}
