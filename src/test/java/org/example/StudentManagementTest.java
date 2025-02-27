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
        assertTrue(studentManagement.addStudent(1, "John Doe", 80.0));
        assertEquals(1, studentManagement.getAllStudents().size());
    }

    @Test
    void testAddDuplicateStudent() {
        studentManagement.addStudent(2, "Alice Smith", 90.0);
        assertFalse(studentManagement.addStudent(2, "Alice Smith", 90.0)); // Duplicate ID
    }

    @Test
    void testUpdateStudentGrade() {
        studentManagement.addStudent(3, "Bob Johnson", 78.0);
        assertTrue(studentManagement.updateStudentGrade(3, 85.0));
        assertEquals(85.0, studentManagement.getStudentById(3).getGrade());
    }

    @Test
    void testUpdateNonExistingStudent() {
        assertFalse(studentManagement.updateStudentGrade(99, 75.0)); // ID does not exist
    }

    @Test
    void testRemoveStudent() {
        studentManagement.addStudent(4, "Charlie Brown", 70.0);
        assertTrue(studentManagement.removeStudent(4));
        assertNull(studentManagement.getStudentById(4));
    }

    @Test
    void testRemoveNonExistingStudent() {
        assertFalse(studentManagement.removeStudent(99)); // ID does not exist
    }

    @Test
    void testGetStudentById() {
        studentManagement.addStudent(5, "David Green", 88.0);
        Student student = studentManagement.getStudentById(5);
        assertNotNull(student);
        assertEquals("David Green", student.getName());
    }

    @Test
    void testGetAllStudents() {
        studentManagement.addStudent(6, "Eve White", 95.0);
        studentManagement.addStudent(7, "Frank Black", 85.5);
        List<Student> students = studentManagement.getAllStudents();
        assertEquals(2, students.size());
    }
}
