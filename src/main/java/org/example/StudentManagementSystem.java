package org.example;

import java.util.ArrayList;
import java.util.List;

public class StudentManagementSystem {
    private List<Student> students;  // Stores students

    // Constructor to initialize student list
    public StudentManagementSystem() {
        this.students = new ArrayList<>();
    }

    // Method to add a student
    public boolean addStudent(int id, String name, double grade) {
        if (getStudentById(id) != null) {
            System.out.println("Error: Student with ID " + id + " already exists.");
            return false;
        }
        Student newStudent = new Student(id, name, grade);
        students.add(newStudent);
        return true;
    }

    // Method to get all students
    public List<Student> getAllStudents() {
        return students;
    }

    // Method to get a student by ID
    public Student getStudentById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    // Method to update a student's grade
    public boolean updateStudentGrade(int id, double newGrade) {
        Student student = getStudentById(id);
        if (student != null) {
            student.setGrade(newGrade);
            return true;
        }
        return false; // Student not found
    }

    // Method to remove a student by ID
    public boolean removeStudent(int id) {
        return students.removeIf(student -> student.getId() == id);
    }

    // Method to display all students
    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            for (Student student : students) {
                System.out.println(student);
            }
        }
    }
}
