# Library Management System (Java)

A console-based library catalog and lending system built with **Java 17+**. Designed to demonstrate core Object-Oriented Programming (OOP), Java Collections, custom exception handling, and binary object serialization with zero external dependencies.

---

## Features

- **Catalog Management**: Add and list books and magazines with unique IDs, categories, and availability status.
- **Borrowing & Returning**: Issue items to members and process returns with automatic availability checks.
- **Flexible Search**: Look up items by exact ID or partial title keyword using overloaded `search()` methods.
- **Persistent Storage**: Automatically serializes and reloads the entire catalog to `library.dat` across sessions.
- **Robust Error Handling**: Uses custom checked exceptions (`ItemNotAvailableException`) and safe wrapper parsing (`Integer.parseInt`) to handle invalid input gracefully.

---

## Java Concepts Demonstrated

- **OOP & Polymorphism**: `Borrowable` interface, abstract `LibraryItem` base class, and `Book` / `Magazine` subclasses overriding `getItemDetails()`.
- **Method Overloading**: Dual `search()` signatures in `LibraryManager` for ID lookup vs. keyword filtering.
- **Collections & Generics**: `HashMap<String, LibraryItem>` for $O(1)$ ID lookups, `HashSet<String>` for unique categories, `ArrayList` for search results, and `Iterator` traversal.
- **I/O & Serialization**: `ObjectOutputStream` and `ObjectInputStream` (`Serializable`) for binary persistence.
- **Exception Handling**: Custom checked exceptions with `try-with-resources` stream management.

---

## Project Structure

```text
library-management-system/
├── .gitignore
├── README.md
└── src/
    └── com/library/
        ├── exception/
        │   └── ItemNotAvailableException.java
        ├── model/
        │   ├── Borrowable.java
        │   ├── LibraryItem.java
        │   ├── Book.java
        │   └── Magazine.java
        ├── service/
        │   └── LibraryManager.java
        └── Main.java
```

---

## Prerequisites

- **Java Development Kit (JDK)**: Version 17 or higher
- **IDE (Optional)**: IntelliJ IDEA (Community Edition) or VS Code with *Extension Pack for Java*

---

## Getting Started

### Option 1: Run in IntelliJ IDEA

1. Clone the repository and open the folder in **IntelliJ IDEA**.
2. Ensure Project SDK is set to **JDK 17+** (**File → Project Structure → Project**).
3. Open `src/com/library/Main.java` and click the green **Run (▶)** button next to `main()`.

### Option 2: Compile & Run via Terminal

```bash
# 1. Clone the repository
git clone https://github.com/s1ngh-divyanshi/<repo-name>.git
cd <repo-name>

# 2. Compile all Java sources into out/
mkdir -p out
javac -d out $(find src -name "*.java")

# 3. Run the application
java -cp out com.library.Main
```

*(On Windows Command Prompt, compile with `javac -d out src\com\library\exception\*.java src\com\library\model\*.java src\com\library\service\*.java src\com\library\Main.java`).*

---

## License

Distributed under the [MIT License](https://www.google.com/search?q=LICENSE).
