package edu.sabanciuniv.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    private String studentName;
    private String studentBirthDate;
    private String studentAddress;
    @Enumerated(EnumType.STRING)
    private Gender studentGender;
    @ManyToMany(mappedBy = "studentList")
    private List<Course> studentCourseList = new ArrayList<>();

    public enum Gender{
        MALE,
        FEMALE
    }

    public Student() {
    }

    public Student(String studentName, String studentBirthDate, String studentAddress, Gender studentGender) {
        this.studentName = studentName;
        this.studentBirthDate = studentBirthDate;
        this.studentAddress = studentAddress;
        this.studentGender = studentGender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentBirthDate() {
        return studentBirthDate;
    }

    public void setStudentBirthDate(String studentBirthDate) {
        this.studentBirthDate = studentBirthDate;
    }

    public String getStudentAddress() {
        return studentAddress;
    }

    public void setStudentAddress(String studentAddress) {
        this.studentAddress = studentAddress;
    }

    public Gender getStudentGender() {
        return studentGender;
    }

    public void setStudentGender(Gender studentGender) {
        this.studentGender = studentGender;
    }

    public List<Course> getStudentCourseList() {
        return studentCourseList;
    }

    public void setStudentCourseList(List<Course> studentCourseList) {
        this.studentCourseList = studentCourseList;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", studentName='" + studentName + '\'' +
                ", studentBirthDate='" + studentBirthDate + '\'' +
                ", studentAddress='" + studentAddress + '\'' +
                ", studentGender=" + studentGender +
                '}';
    }
}
