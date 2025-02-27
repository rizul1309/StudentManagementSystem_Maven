package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

class StudentManagementTest {
    private StudentManagementSystem studentManagement;

    @BeforeEach
    void setUp() {
        studentManagement = new StudentManagementSystem();
    }

    @Test
    void testAddStudent() {
        studentManagement.addStudent(1, "John Doe", 80.0);
        List<Student> students = studentManagement.getStudents();

        assertEquals(1, students.size());
        assertEquals("John Doe", students.get(0).getName());
    }

    @Test
    void testUpdateStudentGrade() {
        studentManagement.addStudent(2, "Jane Doe", 85.0);
        boolean updated = studentManagement.updateGrade(2, 90.0);

        assertTrue(updated);
        assertEquals(90.0, studentManagement.getStudentById(2).getGrade());
    }

    @Test
    void testDeleteStudent() {
        studentManagement.addStudent(3, "Alice", 88.0);
        boolean removed = studentManagement.removeStudent(3);

        assertTrue(removed);
        assertNull(studentManagement.getStudentById(3));
    }

    @Test
    void testGetStudentById() {
        studentManagement.addStudent(4, "Bob", 70.0);
        Student student = studentManagement.getStudentById(4);

        assertNotNull(student);
        assertEquals("Bob", student.getName());
    }
}