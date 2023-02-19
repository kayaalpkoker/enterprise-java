package edu.sabanciuniv.test;

import edu.sabanciuniv.model.School;
import edu.sabanciuniv.model.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;

public class TestStudent {

    public static void main(String[] args) {

        Student student1 = new Student("Kaya", "152", 4.0);
        Student student2 = new Student("Melis", "167", 3.5);
        Student student3 = new Student("Kuman", "189", 3.9);

        School school1 = new School("Sabancı University", "Altunizade");
        School school2 = new School("Bilgi University", "Eyüpsultan");

        List<Student> studentList = new ArrayList<>();
        studentList.add(student1);
        studentList.add(student2);
        studentList.add(student3);

        List<School> schoolList = new ArrayList<>();
        schoolList.add(school1);
        schoolList.add(school2);

        student1.setStudentSchool(school1);
        student2.setStudentSchool(school2);
        student3.setStudentSchool(school2);

        // Need different entityManager for each DB!
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqlPU");
        EntityManager entityManager = emf.createEntityManager();

        saveSchools(entityManager, schoolList);
        saveStudents(entityManager, studentList);

        //findAllStudents(entityManager);
        //findByStudentName(entityManager, student3.getStudentName());
        //updateStudentGPA(entityManager, student2.getStudentNumber(), 2.8);
        //deleteStudent(entityManager, student1.getStudentNumber());

        //findAllSchools(entityManager);
        //updateStudentSchool(entityManager, student2.getStudentNumber(), 2L); //schoolId has to be given directly


    }

    private static void saveSchools(EntityManager entityManager, List<School> schoolList) {
        entityManager.getTransaction().begin();
        for (School school : schoolList) {
            entityManager.persist(school);
        }
        entityManager.getTransaction().commit();
        System.out.println("Number of schools saved: " + schoolList.toArray().length);
    }

    private static void findAllSchools(EntityManager entityManager) {
        List<School> schoolList = entityManager.createQuery("FROM School s", School.class).getResultList();
        for (School school : schoolList) {
            System.out.println(school);
        }
    }

    private static void updateStudentSchool(EntityManager entityManager, String studentNumber, Long schoolId) {
        entityManager.getTransaction().begin();

        Student foundStudent = entityManager.createQuery("FROM Student s WHERE s.studentNumber = :stdNumber", Student.class)
                .setParameter("stdNumber", studentNumber).getSingleResult();

        //School foundSchool = entityManager.find(School.class, schoolId);
        School foundSchool = entityManager.createQuery("FROM School s WHERE s.id = :schoolId", School.class)
                .setParameter("schoolId", schoolId).getSingleResult();

        if (foundSchool == null) {
            System.out.println("School with ID " + schoolId + " not found.");
            entityManager.getTransaction().rollback();
            return;
        }

        foundStudent.setStudentSchool(foundSchool);
        entityManager.merge(foundStudent);

        entityManager.getTransaction().commit();
        System.out.println("Student no. " + studentNumber + "'s school updated to: " + foundSchool.getSchoolName());

    }


    private static void saveStudents(EntityManager entityManager, List<Student> studentList) {
        entityManager.getTransaction().begin();
        for (Student student : studentList) {
            entityManager.persist(student);
        }
        entityManager.getTransaction().commit();
        System.out.println("Number of students saved: " + studentList.toArray().length);
    }

    private static void findAllStudents(EntityManager entityManager) {
        TypedQuery studentJpql = entityManager.createQuery("FROM Student s", Student.class);
        List<Student> studentList = studentJpql.getResultList();

        for (Student student : studentList) {
            System.out.println(student);
        }
    }

    private static void findByStudentName(EntityManager entityManager, String studentName) {
        // Alternative1
        //TypedQuery<Student> studentJpql = entityManager.createQuery("FROM Student s WHERE s.studentName = :stdName", Student.class);
        //studentJpql.setParameter("stdName", studentName);
        //List<Student> foundStudents = studentJpql.getResultList(); //Using .getResultList instead of .getSingleResult()

        // Alternative 2
        List<Student> foundStudents = entityManager.createQuery("FROM Student s WHERE s.studentName = ?1", Student.class)
                .setParameter(1, studentName).getResultList();

        if (foundStudents.isEmpty()) {
            System.out.println("No student with name " + studentName + " was found.");
        }
        else {
            System.out.println("Found students: " + foundStudents);
        }
    }

    private static void updateStudentGPA(EntityManager entityManager, String studentNumber, double newGPA) {
        entityManager.getTransaction().begin();

        Student foundStudent = entityManager.createQuery("FROM Student s WHERE s.studentNumber = :stdNumber", Student.class)
                .setParameter("stdNumber", studentNumber).getSingleResult();

        foundStudent.setStudentGPA(newGPA);
        entityManager.merge(foundStudent); // merge() > Eğer değer varsa update et, yoksa persist(save)

        entityManager.getTransaction().commit();

        System.out.println("Student no. " + studentNumber + "'s GPA updated to: " + newGPA);
    }

    // Object cannot be deleted directly, needs to be found first via a query!
    private static void deleteStudent(EntityManager entityManager, String studentNumber) {
        entityManager.getTransaction().begin();
        Student foundStudent = entityManager.createQuery("FROM Student s WHERE s.studentNumber = :stdNumber", Student.class)
                .setParameter("stdNumber", studentNumber).getSingleResult();
        entityManager.remove(foundStudent); // Remove!
        entityManager.getTransaction().commit();

        System.out.println("Student no: " + studentNumber + " deleted!");


    }

}
