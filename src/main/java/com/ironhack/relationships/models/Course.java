package com.ironhack.relationships.models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String courseName;
    private int courseCredit;
    private String teacherName;
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "course")
    // Esta lista es igual al resultado de SELECT * FROM students WHERE course_id = mi_id;
    private Set<Student> students;



    public Course(String courseName, int courseCredit, String teacherName) {
        this.courseName = courseName;
        this.courseCredit = courseCredit;
        this.teacherName = teacherName;
    }

    public Course() {}


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCourseCredit() {
        return courseCredit;
    }

    public void setCourseCredit(int courseCredit) {
        this.courseCredit = courseCredit;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }
}
