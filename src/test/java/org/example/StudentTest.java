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
    void testSetGrade() {
        Student student = new Student(2, "Jane Doe", 90.0);
        student.setGrade(95.0);
        assertEquals(95.0, student.getGrade());
    }
}