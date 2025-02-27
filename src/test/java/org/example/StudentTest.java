package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    @Test
    void testStudentCreation() {
        Student student = new Student(1, "John Doe", 85.0);
        assertEquals(1, student.getId());
        assertEquals("John Doe", student.getName());
        assertEquals(85.0, student.getGrade());
    }

    @Test
    void testSetValidGrade() {
        Student student = new Student(2, "Alice Smith", 78.5);
        student.setGrade(90.0);
        assertEquals(90.0, student.getGrade());
    }

    @Test
    void testSetInvalidGrade() {
        Student student = new Student(3, "Bob Johnson", 88.0);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            student.setGrade(-5.0);
        });
        assertEquals("Grade must be between 0 and 100.", exception.getMessage());
    }

    @Test
    void testSetValidName() {
        Student student = new Student(4, "Charlie Brown", 92.0);
        student.setName("Charlie Updated");
        assertEquals("Charlie Updated", student.getName());
    }

    @Test
    void testSetInvalidName() {
        Student student = new Student(5, "David Green", 85.0);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            student.setName("");
        });
        assertEquals("Student name cannot be null or empty.", exception.getMessage());
    }
}
