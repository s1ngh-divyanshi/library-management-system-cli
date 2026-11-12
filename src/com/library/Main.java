package com.library;

import com.library.exception.ItemNotAvailableException;
import com.library.model.Book;
import com.library.model.LibraryItem;
import com.library.model.Magazine;
import com.library.service.LibraryManager;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        LibraryManager manager = new LibraryManager("library.dat");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Library Management System ===");
            System.out.println("1. Add Book");
            System.out.println("2. Add Magazine");
            System.out.println("3. List All Items");
            System.out.println("4. Search by Title");
            System.out.println("5. Borrow Item");
            System.out.println("6. Return Item");
            System.out.println("7. Exit");
            System.out.print("Choose an option [1-7]: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
                continue;
            }

            if (choice == 7) {
                System.out.println("Goodbye!");
                break;
            }

            try {
                switch (choice) {
                    case 1 -> {
                        System.out.print("Book ID (e.g. B101): ");
                        String id = scanner.nextLine().trim();
                        System.out.print("Title: ");
                        String title = scanner.nextLine().trim();
                        System.out.print("Category: ");
                        String category = scanner.nextLine().trim();
                        System.out.print("Author: ");
                        String author = scanner.nextLine().trim();
                        System.out.print("Pages: ");
                        int pages = Integer.parseInt(scanner.nextLine().trim());

                        manager.addItem(new Book(id, title, category, author, pages));
                        System.out.println("Book added and saved.");
                    }
                    case 2 -> {
                        System.out.print("Magazine ID (e.g. M201): ");
                        String id = scanner.nextLine().trim();
                        System.out.print("Title: ");
                        String title = scanner.nextLine().trim();
                        System.out.print("Category: ");
                        String category = scanner.nextLine().trim();
                        System.out.print("Issue Number: ");
                        int issue = Integer.parseInt(scanner.nextLine().trim());

                        manager.addItem(new Magazine(id, title, category, issue));
                        System.out.println("Magazine added and saved.");
                    }
                    case 3 -> manager.listAllItems();
                    case 4 -> {
                        System.out.print("Enter title keyword: ");
                        String keyword = scanner.nextLine().trim();
                        List<LibraryItem> found = manager.search(keyword, true);
                        if (found.isEmpty()) {
                            System.out.println("No matching items found.");
                        } else {
                            found.forEach(item -> System.out.println(item.getItemDetails()));
                        }
                    }
                    case 5 -> {
                        System.out.print("Enter Item ID to borrow: ");
                        String id = scanner.nextLine().trim();
                        System.out.print("Member Name: ");
                        String member = scanner.nextLine().trim();
                        manager.borrowItem(id, member);
                        System.out.println("Item borrowed successfully.");
                     }
                    case 6 -> {
                        System.out.print("Enter Item ID to return: ");
                        String id = scanner.nextLine().trim();
                        manager.returnItem(id);
                        System.out.println("Item returned successfully.");
                    }
                    default -> System.out.println("Invalid option. Choose 1-7.");
                }
            } catch (ItemNotAvailableException | NumberFormatException e) {
                System.out.println("Operation failed: " + e.getMessage());
            }
        }
        scanner.close();
    }
}