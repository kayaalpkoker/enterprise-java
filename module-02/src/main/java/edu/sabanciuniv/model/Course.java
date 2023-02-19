package edu.sabanciuniv.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    private String courseName;
    private String courseCode;
    private int courseCreditScore;
    @ManyToOne
    private Instructor courseInstructor;
    @ManyToMany//(mappedBy = "studentCourseList")
    private List<Student> studentList = new ArrayList<>();

    public Course() {
    }

    public Course(String courseName, String courseCode, int courseCreditScore) {
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.courseCreditScore = courseCreditScore;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public int getCourseCreditScore() {
        return courseCreditScore;
    }

    public void setCourseCreditScore(int courseCreditScore) {
        this.courseCreditScore = courseCreditScore;
    }

    public Instructor getCourseInstructor() {
        return courseInstructor;
    }

    public void setCourseInstructor(Instructor courseInstructor) {
        this.courseInstructor = courseInstructor;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", courseName='" + courseName + '\'' +
                ", courseCode=" + courseCode +
                ", courseCreditScore=" + courseCreditScore +
                ", courseInstructor=" + courseInstructor +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return courseCode == course.courseCode && Objects.equals(courseName, course.courseName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseName, courseCode);
    }
}
