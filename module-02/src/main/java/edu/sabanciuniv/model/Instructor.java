package edu.sabanciuniv.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    private String instructorName;
    private String instructorAddress;
    private Long instructorPhoneNumber;
    @OneToMany(mappedBy = "courseInstructor")
    private List<Course> instructorCourseList = new ArrayList<>();

    public Instructor() {
    }

    public Instructor(String instructorName, String instructorAddress, Long instructorPhoneNumber) {
        this.instructorName = instructorName;
        this.instructorAddress = instructorAddress;
        this.instructorPhoneNumber = instructorPhoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public String getInstructorAddress() {
        return instructorAddress;
    }

    public void setInstructorAddress(String instructorAddress) {
        this.instructorAddress = instructorAddress;
    }

    public Long getInstructorPhoneNumber() {
        return instructorPhoneNumber;
    }

    public void setInstructorPhoneNumber(Long instructorPhoneNumber) {
        this.instructorPhoneNumber = instructorPhoneNumber;
    }

    public List<Course> getInstructorCourseList() {
        return instructorCourseList;
    }

    public void setInstructorCourseList(List<Course> instructorCourseList) {
        this.instructorCourseList = instructorCourseList;
    }

    @Override
    public String toString() {
        return "Instructor{" +
                "id=" + id +
                ", instructorName='" + instructorName + '\'' +
                ", instructorAddress='" + instructorAddress + '\'' +
                ", instructorPhoneNumber='" + instructorPhoneNumber + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Instructor that = (Instructor) o;
        return Objects.equals(instructorName, that.instructorName) && Objects.equals(instructorPhoneNumber, that.instructorPhoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(instructorName, instructorPhoneNumber);
    }
}
