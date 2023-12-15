package org.example;
// Разработайте класс Student с полями String name, int age, transient double GPA (средний балл).
// Обеспечьте поддержку сериализации для этого класса.
// Создайте объект класса Student и инициализируйте его данными.
// Сериализуйте этот объект в файл.
// Десериализуйте объект обратно в программу из файла.
// Выведите все поля объекта, включая GPA, и обсудите, почему значение GPA не было сохранено/восстановлено.
// 2. * Выполнить задачу 1 используя другие типы сериализаторов (в xml и json документы).

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.example.practic.task2.ToDo;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.out.println("Hello world!");
        Student student1 = new Student("Ioan", 20, 70.5);
        System.out.println(student1);
        try (FileOutputStream fileOutputStream = new FileOutputStream("student");
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(student1);
        }
        Student student2 = new Student();
        try (FileInputStream fileInputStream = new FileInputStream("student");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)){
            student2 = (Student) objectInputStream.readObject();
        }
        System.out.println(student2);
        System.out.println("Поле GPA не сохраняется, так как помечено как transient, из за чего игнорирует сериализацию и равно - " + student2.getGPA());
        // 2. * Выполнить задачу 1 используя другие типы сериализаторов (в xml и json документы).
        List<Student> studentList = new ArrayList<>();
        studentList.add(student1);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
            objectMapper.writeValue(new File("studentJson.json"), studentList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        XmlMapper xmlMapper = new XmlMapper();
        try {
            xmlMapper.writeValue(new File("studentXml.xml"), studentList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Сериализация прошла успешно");
        // десериализация ПРОБЛЕМА!!!!!!!!!!!!!!!!!!!!!!!!!
        List<Student> tasks1 = new ArrayList<>();
        try {
            tasks1 = objectMapper.readValue("studentJson.json", objectMapper.getTypeFactory().constructCollectionType(List.class, Student.class));
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
      ///  System.out.println(tasks1);
        List<Student> tasks2 = new ArrayList<>();
        try {
            tasks2 = xmlMapper.readValue("studentXml.xml", xmlMapper.getTypeFactory().constructCollectionType(List.class, Student.class));
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
       // System.out.println(tasks2);
    }
}