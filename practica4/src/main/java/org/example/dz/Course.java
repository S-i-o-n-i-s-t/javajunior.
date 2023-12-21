package org.example.dz;

import javax.persistence.*;
import java.util.Random;

@Entity
// @Table(name = "SchoolDB.Courses")
@Table(name = "Courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "title") private String title;
    @Column(name = "duration") private int duration;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Course() {
    }

    public Course(int id, String title, int duration) {
        this.id = id;
        this.title = title;
        this.duration = duration;
    }

    public Course(String title, int duration) {
        this.title = title;
        this.duration = duration;
    }
    public void upDateTitle(){
        String[] listTitle = {"Литература","История","Маьематика","Физика","Химия","Биология","Музыка","Основы академического рисунка","Геодезия"};
        int random = new Random().nextInt(0, listTitle.length- 1);
        title = listTitle[random];
    }
    public void upDateDuration(){
        duration = new Random().nextInt(1, 30);
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", duration=" + duration +
                '}';
    }
}
