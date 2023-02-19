package edu.sabanciuniv.model;

import jakarta.persistence.*;

import java.util.Objects;

//POJO
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String studentName;
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(unique = true)
    private String studentNumber;
    private double studentGPA;

    @ManyToOne
    private School studentSchool;

    public Student() {
    }

    public Student(String studentName, String studentNumber, double studentGPA) {
        this.studentName = studentName;
        this.studentNumber = studentNumber;
        this.studentGPA = studentGPA;
    }

    public Long getId() {
        return id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public double getStudentGPA() {
        return studentGPA;
    }

    public void setStudentGPA(double GPA) {
        this.studentGPA = GPA;
    }

    public School getStudentSchool() {
        return studentSchool;
    }

    public void setStudentSchool(School school) {
        this.studentSchool = school;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return studentName.equals(student.studentName) && studentNumber.equals(student.studentNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentName, studentNumber);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", studentName='" + studentName + '\'' +
                ", studentNumber='" + studentNumber + '\'' +
                ", GPA=" + studentGPA +
                '}';
    }
}
