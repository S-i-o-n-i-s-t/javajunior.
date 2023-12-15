package org.example.practic.task1;

import java.io.*;

public class Program1 {
    public static void main(String[] args) throws ClassNotFoundException, IOException {
        UserDate userDate = new UserDate("Name", 12, "123");
        System.out.println(userDate);


        try (FileOutputStream fileOutputStream = new FileOutputStream("userData.bin");

             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(userDate);
        }
        System.out.println("Сериализация выполнена");

        try (FileInputStream fileInputStream = new FileInputStream("userData.bin");
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            userDate = (UserDate) objectInputStream.readObject();
        }
        System.out.println("Десериализация выполнена");
        System.out.println(userDate);
    }
}
