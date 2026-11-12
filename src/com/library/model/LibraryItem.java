package com.library.model;

import com.library.exception.ItemNotAvailableException;
import java.io.Serializable;

public abstract class LibraryItem implements Borrowable, Serializable {
    private static final long serialVersionUID = 1L;

    private final String id;
    private final String title;
    private final String category;
    private boolean isBorrowed;
    private String borrowedBy;

    public LibraryItem(String id, String title, String category) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.isBorrowed = false;
        this.borrowedBy = null;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getCategory() { return category; }
    public boolean isBorrowed() { return isBorrowed; }
    public String getBorrowedBy() { return borrowedBy; }

    @Override
    public void borrowItem(String memberName) throws ItemNotAvailableException {
        if (isBorrowed) {
            throw new ItemNotAvailableException(
                    "Item '" + title + "' is already borrowed by " + borrowedBy
            );
        }
        this.isBorrowed = true;
        this.borrowedBy = memberName;
    }

    @Override
    public void returnItem() throws ItemNotAvailableException {
        if (!isBorrowed) {
            throw new ItemNotAvailableException("Item '" + title + "' is not currently borrowed.");
        }
        this.isBorrowed = false;
        this.borrowedBy = null;
    }

    // Polymorphic method implemented by subclasses
    public abstract String getItemDetails();
}