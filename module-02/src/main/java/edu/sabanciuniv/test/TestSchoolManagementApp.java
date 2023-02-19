package edu.sabanciuniv.test;

import edu.sabanciuniv.model.*;
import edu.sabanciuniv.utility.EntityManagerUtils;
import jakarta.persistence.EntityManager;

public class TestSchoolManagementApp {

    public static void main(String[] args) {
        saveTestDate();


    }

    private static void saveTestDate() {

        Student student1 = new Student("Kaya","29/11/1993","Kadıköy", Student.Gender.MALE);
        Student student2 = new Student("Melis","19/10/1994","Fenerbahçe", Student.Gender.FEMALE);
        Student student3 = new Student("Can","01/01/1990","Bostancı", Student.Gender.MALE);

        Course course1 = new Course("Enterprise Java","IT526",12);
        Course course2 = new Course("Software Testing","IT538",8);
        Course course3 = new Course("Big Data with Hadoop","IT555",10);
        Course course4 = new Course("Spring Bootcamp","IT577",10);
        Course course5 = new Course("Introduction to CS","IT501",8);

        Instructor visiting1 = new VisitingResearcher("Koray Güney","Tuzla",535123777L,20.5);
        Instructor visiting2 = new VisitingResearcher("Ömer Karacan","Vienna",535123888L,20.5);
        Instructor permanent1 = new PermanentInstructor("Ahmet Demirelli","Bostancı",535123999L,22.7);

        course1.setCourseInstructor(visiting1);
        course2.setCourseInstructor(visiting2);
        course3.setCourseInstructor(permanent1);
        course4.setCourseInstructor(visiting1);
        course5.setCourseInstructor(permanent1);

        course1.getStudentList().add(student1);
        course1.getStudentList().add(student2);
        course2.getStudentList().add(student1);
        course2.getStudentList().add(student2);
        course4.getStudentList().add(student1);
        course5.getStudentList().add(student3);

        EntityManager entityManager = EntityManagerUtils.getEntityManager("mysqlPU");

        try {
            entityManager.getTransaction().begin();

            entityManager.persist(course1);
            entityManager.persist(course2);
            entityManager.persist(course3);
            entityManager.persist(course4);
            entityManager.persist(course5);

            entityManager.persist(student1);
            entityManager.persist(student2);
            entityManager.persist(student3);

            entityManager.persist(visiting1);
            entityManager.persist(visiting2);
            entityManager.persist(permanent1);

            entityManager.getTransaction().commit();
            System.out.println("All data persisted!...");

        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        } finally {
            EntityManagerUtils.closeEntityManager(entityManager);
        }

    }

}
