package org.example;

import java.io.*;

public class Student implements Serializable {
    private String name;
    private int age;
    private transient double GPA;

    public Student(String name, int age, double GPA) {
        this.name = name;
        this.age = age;
        this.GPA = GPA;
    }
    public Student() {
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getGPA() {
        return GPA;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", GPA=" + GPA +
                '}';
    }

//    @Override
//    public void writeExternal(ObjectOutput out) throws IOException {
//        out.writeObject(name);
//        out.writeObject(age);
//        out.writeObject(GPA);
//    }
//
//    @Override
//    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
////       name = (String) in.readObject();
////        age = (int) in.readObject();
////        GPA = (double) in.readObject();
//    }
}
