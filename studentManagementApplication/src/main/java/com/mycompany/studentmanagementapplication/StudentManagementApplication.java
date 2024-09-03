package com.mycompany.studentmanagementapplication;

import java.util.ArrayList;
import java.util.Scanner;

class Student {
    private String studentId;
    private String name;
    private String dateOfBirth;
    private int age;
    private String course;

    private static ArrayList<Student> students = new ArrayList<>();

    public Student(String studentId, String name, String dateOfBirth, int age, String course) {
        this.studentId = studentId;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.age = age;
        this.course = course;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public int getAge() {
        return age;
    }

    public String getCourse() {
        return course;
    }
    
    public String toString() {
        return "Student ID: " + studentId + "\nName: " + name + "\nDate of Birth: " + dateOfBirth + "\nAge: " + age + "\nCourse: " + course;
    }

    public static void saveStudent(Scanner scanner) {
        System.out.println("Enter Student ID:");
        String studentId = scanner.nextLine();

        System.out.println("Enter Name:");
        String name = scanner.nextLine();

        System.out.println("Enter Date of Birth (YYYY-MM-DD):");
        String dateOfBirth = scanner.nextLine();

        int age = 0;
        boolean validAge = false;

        while (!validAge) {
            System.out.println("Enter Age (must be 16 or older):");
            String ageInput = scanner.nextLine();

            try {
                age = Integer.parseInt(ageInput);
                if (age >= 16) {
                    validAge = true;
                } else {
                    System.out.println("Invalid age. Age must be 16 or older. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number for age.");
            }
        }

        System.out.println("Enter Course:");
        String course = scanner.nextLine();

        Student newStudent = new Student(studentId, name, dateOfBirth, age, course);
        students.add(newStudent);

        System.out.println("Student details have been successfully saved.");
    }

    public static void searchStudent(Scanner scanner) {
        System.out.println("Enter Student ID to search:");
        String studentId = scanner.nextLine();

        for (Student student : students) {
            if (student.getStudentId().equals(studentId)) {
                System.out.println("Student found:");
                System.out.println(student);
                return;
            }
        }
        System.out.println("Error: Student cannot be located.");
    }

    public static void deleteStudent(Scanner scanner) {
        System.out.println("Enter Student ID to delete:");
        String studentId = scanner.nextLine();

        for (Student student : students) {
            if (student.getStudentId().equals(studentId)) {
                System.out.println("Are you sure you want to delete " + studentId + " from the system? Yes (Y) to delete.");
                String confirmation = scanner.nextLine();

                if (confirmation.equalsIgnoreCase("Y")) {
                    students.remove(student);
                    System.out.println("Student " + studentId + " has been deleted from the system.");
                } else {
                    System.out.println("Student deletion canceled.");
                }
                return;
            }
        }
        System.out.println("Error: Student cannot be located.");
    }

    public static void printStudentReport() {
        if (students.isEmpty()) {
            System.out.println("No students to display.");
        } else {
            System.out.println("Student Report:");
            for (Student student : students) {
                System.out.println(student);
                System.out.println("-------------------------");
            }
        }
    }

    public static void exitStudentApplication() {
        System.out.println("Exiting application...");
        System.exit(0);
    }
}

public class StudentManagementApplication {

    public static void main(String[] args) {
        displayInitialMenu();
    }

    public static void displayInitialMenu() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Student ID");
        System.out.println("***************************************");
        System.out.println("Enter (1) to launch menu or any other key to exit");

        String input = scanner.nextLine();

        if (input.equals("1")) {
            displayMainMenu();
        } else {
            System.out.println("Exiting...");
            Student.exitStudentApplication();
        }
        scanner.close();
    }

    public static void displayMainMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Please select one of the following menu items:");
            System.out.println("1) Capture new student");
            System.out.println("2) Search for a student");
            System.out.println("3) Delete a student");
            System.out.println("4) Print student report");
            System.out.println("5) Exit application");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    Student.saveStudent(scanner);
                    break;
                case 2:
                    Student.searchStudent(scanner);
                    break;
                case 3:
                    Student.deleteStudent(scanner);
                    break;
                case 4:
                    Student.printStudentReport();
                    break;
                case 5:
                    Student.exitStudentApplication();
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        } while (choice != 5);

        scanner.close();
    }
}
