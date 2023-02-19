package edu.sabanciuniv.model;

import jakarta.persistence.Entity;

@Entity
public class VisitingResearcher extends Instructor {

    private double hourlySalary;

    public VisitingResearcher() {
    }

    public VisitingResearcher(double hourlySalary) {
        this.hourlySalary = hourlySalary;
    }

    public VisitingResearcher(String instructorName, String instructorAddress, Long instructorPhoneNumber, double hourlySalary) {
        super(instructorName, instructorAddress, instructorPhoneNumber);
        this.hourlySalary = hourlySalary;
    }

    public double getHourlySalary() {
        return hourlySalary;
    }

    public void setHourlySalary(double hourlySalary) {
        this.hourlySalary = hourlySalary;
    }

    @Override
    public String toString() {
        return "VisitingResearcher{" +
                "hourlySalary=" + hourlySalary +
                '}';
    }
}
