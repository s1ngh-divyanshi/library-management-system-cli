package com.library.service;

import com.library.exception.ItemNotAvailableException;
import com.library.model.LibraryItem;

import java.io.*;
import java.util.*;

public class LibraryManager {
    private Map<String, LibraryItem> catalog;
    private final Set<String> categories;
    private final String dataFilePath;

    public LibraryManager(String dataFilePath) {
        this.dataFilePath = dataFilePath;
        this.catalog = new HashMap<>();
        this.categories = new HashSet<>();
        loadCatalog();
    }

    public void addItem(LibraryItem item) {
        catalog.put(item.getId(), item);
        categories.add(item.getCategory());
        saveCatalog();
    }

    // Method Overloading 1: Search by exact ID
    public LibraryItem search(String id) {
        return catalog.get(id);
    }

    // Method Overloading 2: Search by title keyword (returns List)
    public List<LibraryItem> search(String keyword, boolean matchTitle) {
        List<LibraryItem> results = new ArrayList<>();
        String query = keyword.toLowerCase();

        Iterator<LibraryItem> iterator = catalog.values().iterator();
        while (iterator.hasNext()) {
            LibraryItem item = iterator.next();
            if (item.getTitle().toLowerCase().contains(query)) {
                results.add(item);
            }
        }
        return results;
    }

    public void borrowItem(String id, String memberName) throws ItemNotAvailableException {
        LibraryItem item = catalog.get(id);
        if (item == null) {
            throw new ItemNotAvailableException("No item found with ID: " + id);
        }
        item.borrowItem(memberName);
        saveCatalog();
    }

    public void returnItem(String id) throws ItemNotAvailableException {
        LibraryItem item = catalog.get(id);
        if (item == null) {
            throw new ItemNotAvailableException("No item found with ID: " + id);
        }
        item.returnItem();
        saveCatalog();
    }

    public void listAllItems() {
        if (catalog.isEmpty()) {
            System.out.println("Catalog is empty.");
            return;
        }
        StringBuilder sb = new StringBuilder("\n--- Library Catalog ---\n");
        for (LibraryItem item : catalog.values()) {
            sb.append(item.getItemDetails()).append("\n");
        }
        sb.append("Active Categories: ").append(categories).append("\n");
        System.out.print(sb.toString());
    }

    private void saveCatalog() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(dataFilePath))) {
            oos.writeObject(catalog);
        } catch (IOException e) {
            System.err.println("Error saving catalog: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void loadCatalog() {
        File file = new File(dataFilePath);
        if (!file.exists()) return;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            catalog = (Map<String, LibraryItem>) ois.readObject();
            for (LibraryItem item : catalog.values()) {
                categories.add(item.getCategory());
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Warning: Could not load saved catalog: " + e.getMessage());
        }
    }
}