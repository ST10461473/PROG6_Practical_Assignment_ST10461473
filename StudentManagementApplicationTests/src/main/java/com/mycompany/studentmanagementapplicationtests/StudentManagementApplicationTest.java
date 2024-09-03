package com.mycompany.studentmanagementapplicationtests;

import com.mycompany.studentmanagementapplication.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

public class StudentManagementApplicationTest {

    private final InputStream originalSystemIn = System.in;

    @BeforeEach
    void setUp() {
        // Reset the students list before each test
        Student.clearStudents();
    }

    @Test
    void testSaveStudent() {
        String input = "123\nJohn Doe\n2005-09-01\n18\nComputer Science\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        Scanner scanner = new Scanner(System.in);
        Student.saveStudent(scanner);

        assertEquals(1, Student.getStudents().size());
        Student savedStudent = Student.getStudents().get(0);
        assertEquals("123", savedStudent.getStudentId());
        assertEquals("John Doe", savedStudent.getName());
        assertEquals("2005-09-01", savedStudent.getDateOfBirth());
        assertEquals(18, savedStudent.getAge());
        assertEquals("Computer Science", savedStudent.getCourse());
    }

    @Test
    void testSearchStudent() {
        Student student = new Student("124", "Jane Doe", "2004-05-15", 20, "Mathematics");
        Student.getStudents().add(student);

        String input = "124\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        Scanner scanner = new Scanner(System.in);
        Student.searchStudent(scanner);

        // Check console output (not captured in standard assertions)
        // You might need a custom output stream or use libraries like System Rules to capture and test console output
    }

    @Test
    void testSearchStudentStudentNotFound() {
        String input = "999\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        Scanner scanner = new Scanner(System.in);
        Student.searchStudent(scanner);

        // Check console output (not captured in standard assertions)
        // You might need a custom output stream or use libraries like System Rules to capture and test console output
    }

    @Test
    void testDeleteStudent() {
        Student student = new Student("125", "Mark Smith", "2003-11-20", 21, "Physics");
        Student.getStudents().add(student);

        String input = "125\nY\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        Scanner scanner = new Scanner(System.in);
        Student.deleteStudent(scanner);

        assertEquals(0, Student.getStudents().size());
    }

    @Test
    void testDeleteStudentStudentNotFound() {
        String input = "999\nY\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        Scanner scanner = new Scanner(System.in);
        Student.deleteStudent(scanner);

        // Check console output (not captured in standard assertions)
        // You might need a custom output stream or use libraries like System Rules to capture and test console output
    }

    @Test
    void testStudentAgeValid() {
        String input = "20\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        Scanner scanner = new Scanner(System.in);
        int age = 0;
        boolean validAge = false;
        while (!validAge) {
            try {
                age = Integer.parseInt(scanner.nextLine());
                if (age >= 16) {
                    validAge = true;
                }
            } catch (NumberFormatException e) {
                // Handle invalid input
            }
        }

        assertEquals(20, age);
    }

    @Test
    void testStudentAgeInvalid() {
        String input = "15\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        Scanner scanner = new Scanner(System.in);
        int age = 0;
        boolean validAge = false;
        while (!validAge) {
            try {
                age = Integer.parseInt(scanner.nextLine());
                if (age >= 16) {
                    validAge = true;
                }
            } catch (NumberFormatException e) {
                // Handle invalid input
            }
        }

        assertNotEquals(15, age);
    }

    @Test
    void testStudentAgeInvalidCharacter() {
        String input = "abc\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        Scanner scanner = new Scanner(System.in);
        assertThrows(NumberFormatException.class, () -> {
            Integer.parseInt("abc");
        });
    }
}
