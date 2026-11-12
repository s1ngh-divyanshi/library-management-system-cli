package com.library.model;

public class Magazine extends LibraryItem {
    private static final long serialVersionUID = 1L;
    private final int issueNumber;

    public Magazine(String id, String title, String category, int issueNumber) {
        super(id, title, category);
        this.issueNumber = issueNumber;
    }

    @Override
    public String getItemDetails() {
        return String.format("[MAGAZINE] %s | %s (Issue #%d) | %s",
                getId(), getTitle(), issueNumber,
                isBorrowed() ? "Borrowed by " + getBorrowedBy() : "Available");
    }
}