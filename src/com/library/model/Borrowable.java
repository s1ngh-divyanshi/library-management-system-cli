package com.library.model;
import com.library.exception.ItemNotAvailableException;

public interface Borrowable {
    void borrowItem(String memberName) throws ItemNotAvailableException;
    void returnItem() throws ItemNotAvailableException;
}