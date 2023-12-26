package org.example.dz;


import org.example.primer.homework.models.Student;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.sql.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Создайте базу данных (например, SchoolDB).
        // В этой базе данных создайте таблицу Courses с полями id (ключ), title, и duration.
        try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "2381012")){
            Statement statement = connection.createStatement();
            statement.execute("DROP DATABASE IF EXISTS SchoolDB;");
            statement.execute("CREATE DATABASE SchoolDB;");
            statement.execute("USE SchoolDB;");
            statement.execute("DROP TABLE IF EXISTS Courses;");
            statement.execute("CREATE TABLE Courses (id INT AUTO_INCREMENT PRIMARY KEY, title VARCHAR(45), duration INT);");
            statement.execute("INSERT INTO Courses (title, duration)VALUES('Сопро-мат', 6), ('Тер-мех', 12);");
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Courses;");
            while (resultSet.next()){
                System.out.println(resultSet.getInt(1) + " " + resultSet.getString(2) + " " + resultSet.getInt(3));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        // Настройте Hibernate для работы с вашей базой данных.
        // Создайте Java-класс Course, соответствующий таблице Courses, с необходимыми аннотациями Hibernate.
        // Используя Hibernate, напишите код для вставки, чтения, обновления и удаления данных в таблице Courses.
        // Убедитесь, что каждая операция выполняется в отдельной транзакции.
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        SessionFactory sessionFactory = new MetadataSources(registry)
                .buildMetadata()
                .buildSessionFactory();
        Session session = sessionFactory.openSession();
        Course main2 = new Course("Математика", 12);
        session.beginTransaction();
        session.save(main2);
        session.getTransaction().commit();
        session.close();

        // Добавление
        Connector connector = new Connector();
        session = connector.getSession();
        Course main3 = new Course("Физика", 5);
        session.beginTransaction();
        session.save(main3);
        session.getTransaction().commit();
        session.close();

        // Чтение
        session = connector.getSession();
        try {
            List<Course> title = session.createQuery("FROM Course ", Course.class).getResultList(); // регистр влияет на Course
            title.forEach(System.out::println);
        }catch (Exception e){
            e.printStackTrace();
        }
        session = connector.getSession();
        try {
            // читаем и меняем данные

            String str = "FROM Course WHERE id = :id";
            Query<Course> courseQuery = session.createQuery(str, Course.class);
            courseQuery.setParameter("id", 1);
            Course  course = courseQuery.getSingleResult();
            System.out.println("Прочитали конкретную строку");
            System.out.println(course);
            course.setDuration(20);
            session.beginTransaction();
            session.update(course);
            session.getTransaction().commit();

        }catch (Exception e){
            e.printStackTrace();
        }
        // Обновление объекта
        session = connector.getSession();
        try {
            List<Course> courses = session.createQuery("FROM Course", Course.class).getResultList();
            String str = "FROM Course WHERE id = :id";
            Query<Course> courseQuery = session.createQuery(str, Course.class);
            session.beginTransaction();
            for (int i = 0; i < courses.size(); i++){
                courseQuery.setParameter("id", i+1);
                Course course = courseQuery.getSingleResult();
                course.upDateTitle();
                course.upDateDuration();
                session.update(course);
            }
//            courses.forEach(Course::upDateTitle);
//            courses.forEach(Course::upDateDuration);
//            courses.forEach(System.out::println);
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }
       //  Удаление
        session = connector.getSession();
        try {
            Transaction transaction = session.beginTransaction();
            List<Course> courses = session.createQuery("FROM Course", Course.class).getResultList();
            session.delete(courses.get(0));
            //session.getTransaction().commit();
            transaction.commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}