package org.example.practic.task1;

import java.io.Serializable;

public class UserDate implements Serializable {
    private String name;
    private int age;
    private transient String password;

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

    public String getPassword() {
        return this.password;
    }

    public UserDate(String name, int age, String password) {
        this.name = name;
        this.age = age;
        this.password = password;
    }

    public String toString() {
        return "UserDate{name='" + this.name + "', age=" + this.age + ", password='" + this.password + "'}";
    }
}
