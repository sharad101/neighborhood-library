package com.pluralsight;

import java.util.Scanner;

public class NeighborhoodLibrary {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Book[] books = new Book[20];

        // Only initializing 5 books, but array can hold 20
        books[0] = new Book(1, "978-3-16-148410-0", "To Kill a Mockingbird");
        books[1] = new Book(2, "978-0-06-112008-4", "1984");
        books[2] = new Book(3, "978-0-7432-7356-5", "The Great Gatsby");
        books[3] = new Book(4, "978-1-56619-909-4", "Moby Dick");
        books[4] = new Book(5, "978-0-452-28423-4", "Pride and Prejudice");

        boolean running = true;
        while (running) {
            System.out.println("\nWelcome to the Neighborhood Library!");
            System.out.println("1. Show Available Books");
            System.out.println("2. Show Checked Out Books");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    showAvailableBooks(scanner, books);
                    break;
                case "2":
                    showCheckedOutBooks(scanner, books);
                    break;
                case "3":
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
        scanner.close();
    }

    public static void showAvailableBooks(Scanner scanner, Book[] books) {
        System.out.println("\nAvailable Books:");
        for (Book book : books) {
            if (book != null && !book.isCheckedOut()) {
                System.out.printf("%d | %s | %s%n", book.getId(), book.getIsbn(), book.getTitle());
            }
        }
        System.out.print("Enter book ID to check out or 0 to return: ");
        int id = Integer.parseInt(scanner.nextLine());
        if (id != 0) {
            for (Book book : books) {
                if (book != null && book.getId() == id && !book.isCheckedOut()) {
                    System.out.print("Enter your name: ");
                    String name = scanner.nextLine();
                    book.checkOut(name);
                    System.out.println("Book checked out successfully!");
                    return;
                }
            }
            System.out.println("Book not available or invalid ID.");
        }
    }

    public static void showCheckedOutBooks(Scanner scanner, Book[] books) {
        System.out.println("\nChecked Out Books:");
        for (Book book : books) {
            if (book != null && book.isCheckedOut()) {
                System.out.printf("%d | %s | %s | Checked out to: %s%n",
                        book.getId(), book.getIsbn(), book.getTitle(), book.getCheckedOutTo());
            }
        }
        System.out.print("Enter C to check in a book or X to return: ");
        String input = scanner.nextLine();
        if (input.equalsIgnoreCase("C")) {
            System.out.print("Enter the ID of the book to check in: ");
            int id = Integer.parseInt(scanner.nextLine());
            for (Book book : books) {
                if (book != null && book.getId() == id && book.isCheckedOut()) {
                    book.checkIn();
                    System.out.println("Book checked in successfully!");
                    return;
                }
            }
            System.out.println("Invalid ID or book not checked out.");
        }

    }
}
