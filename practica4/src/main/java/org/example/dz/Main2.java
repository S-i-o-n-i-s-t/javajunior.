package org.example.dz;

import jakarta.persistence.*;


@Entity
// @Table(name = "SchoolDB.Courses")
@Table(name = "Courses")
public class Main2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id") private int id;
    @Column(name = "Title") private String title;
    @Column(name = "Duration") private int duration;

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

    @Override
    public String toString() {
        return "Main2{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", duration=" + duration +
                '}';
    }

    public Main2(String title, int duration) {
        this.title = title;
        this.duration = duration;
    }
}
