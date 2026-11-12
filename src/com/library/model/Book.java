package com.library.model;

public class Book extends LibraryItem {
    private static final long serialVersionUID = 1L;
    private final String author;
    private final int pages;

    public Book(String id, String title, String category, String author, int pages) {
        super(id, title, category);
        this.author = author;
        this.pages = pages;
    }

    @Override
    public String getItemDetails() {
        return String.format("[BOOK] %s | %s by %s (%d pages) | %s",
                getId(), getTitle(), author, pages,
                isBorrowed() ? "Borrowed by " + getBorrowedBy() : "Available");
    }
}