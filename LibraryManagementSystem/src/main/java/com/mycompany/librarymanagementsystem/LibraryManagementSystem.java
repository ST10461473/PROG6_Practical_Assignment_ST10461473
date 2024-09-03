package com.mycompany.librarymanagementsystem;

import java.util.ArrayList;
import java.util.Scanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class LibraryManagementSystem {

    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Library Management System");
            System.out.println("1. Add Book");
            System.out.println("2. List Books");
            System.out.println("3. Search Book");
            System.out.println("4. Borrow Book");
            System.out.println("5. Return Book");
            System.out.println("6. Generate Report");
            System.out.println("7. Exit");

            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.println("Enter book title:");
                    String title = scanner.nextLine();
                    System.out.println("Enter book author:");
                    String author = scanner.nextLine();
                    library.addBook(new Book(title, author));
                    break;
                case 2:
                    library.listBooks();
                    break;
                case 3:
                    System.out.println("Enter book title to search:");
                    title = scanner.nextLine();
                    Book book = library.searchBook(title);
                    if (book != null) {
                        System.out.println(book);
                    } else {
                        System.out.println("Book not found.");
                    }
                    break;
                case 4:
                    System.out.println("Enter book title to borrow:");
                    title = scanner.nextLine();
                    library.borrowBook(title);
                    break;
                case 5:
                    System.out.println("Enter book title to return:");
                    title = scanner.nextLine();
                    library.returnBook(title);
                    break;
                case 6:
                    library.generateReport();
                    break;
                case 7:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 7);

        scanner.close();
    }

    // Nested Library class
    static class Library {
        private final ArrayList<Book> books;

        public Library() {
            books = new ArrayList<>();
        }

        public void addBook(Book book) {
            books.add(book);
            System.out.println("Book added successfully.");
        }

        public void listBooks() {
            if (books.isEmpty()) {
                System.out.println("No books available.");
            } else {
                for (Book book : books) {
                    System.out.println(book);
                }
            }
        }

        public Book searchBook(String title) {
            for (Book book : books) {
                if (book.getTitle().equalsIgnoreCase(title)) {
                    return book;
                }
            }
            return null;
        }

        public void borrowBook(String title) {
            Book book = searchBook(title);
            if (book != null) {
                book.borrow();
            } else {
                System.out.println("Book not found.");
            }
        }

        public void returnBook(String title) {
            Book book = searchBook(title);
            if (book != null) {
                book.returnBook();
            } else {
                System.out.println("Book not found.");
            }
        }

        public void generateReport() {
            System.out.println("Library Report:");
            listBooks();
        }
    }

    // Nested Book class
    static class Book {
        private final String title;
        private final String author;
        private boolean isAvailable;

        public Book(String title, String author) {
            this.title = title;
            this.author = author;
            this.isAvailable = true;
        }

        public String getTitle() {
            return title;
        }

        public String getAuthor() {
            return author;
        }

        public boolean isAvailable() {
            return isAvailable;
        }

        public void borrow() {
            if (isAvailable) {
                isAvailable = false;
            } else {
                System.out.println("Book is not available for borrowing.");
            }
        }

        public void returnBook() {
            if (!isAvailable) {
                isAvailable = true;
            } else {
                System.out.println("Book is already available.");
            }
        }

        @Override
        public String toString() {
            return "Title: " + title + ", Author: " + author + ", Available: " + (isAvailable ? "Yes" : "No");
        }
    }

    // Nested Unit Tests
    public static class LibraryManagementSystemTest {
        private Library library;
        private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        private final PrintStream originalOut = System.out;

        @BeforeEach
        void setUp() {
            library = new Library();
            System.setOut(new PrintStream(outputStream));
        }

        @Test
        void testAddBook() {
            Book book = new Book("Test Title", "Test Author");
            library.addBook(book);
            assertEquals("Book added successfully.\n", outputStream.toString());
        }

        @Test
        void testListBooks() {
            library.addBook(new Book("Book One", "Author One"));
            library.addBook(new Book("Book Two", "Author Two"));
            library.listBooks();
            String output = outputStream.toString();
            assertTrue(output.contains("Title: Book One, Author: Author One, Available: Yes"));
            assertTrue(output.contains("Title: Book Two, Author: Author Two, Available: Yes"));
        }

        @Test
        void testSearchBookFound() {
            library.addBook(new Book("Book One", "Author One"));
            Book book = library.searchBook("Book One");
            assertNotNull(book);
            assertEquals("Book One", book.getTitle());
        }

        @Test
        void testSearchBookNotFound() {
            Book book = library.searchBook("Nonexistent Book");
            assertNull(book);
        }

        @Test
        void testBorrowBook() {
            Book book = new Book("Book One", "Author One");
            library.addBook(book);
            library.borrowBook("Book One");
            assertFalse(book.isAvailable());
        }

        @Test
        void testReturnBook() {
            Book book = new Book("Book One", "Author One");
            library.addBook(book);
            library.borrowBook("Book One");
            library.returnBook("Book One");
            assertTrue(book.isAvailable());
        }

        @Test
        void testGenerateReport() {
            library.addBook(new Book("Book One", "Author One"));
            library.addBook(new Book("Book Two", "Author Two"));
            library.generateReport();
            String output = outputStream.toString();
            assertTrue(output.contains("Title: Book One, Author: Author One, Available: Yes"));
            assertTrue(output.contains("Title: Book Two, Author: Author Two, Available: Yes"));
        }

        @AfterEach
        void tearDown() {
            System.setOut(originalOut);
        }

        private void assertTrue(boolean contains) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        private void assertFalse(boolean available) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        private void assertNull(Book book) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        private void assertEquals(String book_One, String title) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        private void assertNotNull(Book book) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
    }
}
