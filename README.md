# 📚 Student Management System (Maven)

A **Java console-based application** that manages student records — add, view, update, and remove students — built with **Maven** as the build tool and tested with **JUnit 5** and **Mockito**.

---

## 📑 Table of Contents

- [Overview](#overview)
- [Project Architecture](#project-architecture)
- [Prerequisites](#prerequisites)
- [How to Clone & Run](#how-to-clone--run)
- [File-by-File Breakdown](#file-by-file-breakdown)
- [How the Application Works (End-to-End)](#how-the-application-works-end-to-end)
- [Running Tests](#running-tests)
- [Build & Package](#build--package)
- [Project Configuration Explained](#project-configuration-explained)
- [Technologies Used](#technologies-used)

---

## Overview

This project is a **Student Management System** that demonstrates core Java concepts:

- Object-Oriented Programming (encapsulation, constructors, getters/setters)
- Input validation with custom error messages
- Collection management using `ArrayList`
- Unit testing with JUnit 5
- Maven project structure and dependency management

The system allows you to:
| Operation | Description |
|-----------|-------------|
| **Add** | Register a new student with ID, name, and grade |
| **View All** | List every student currently stored |
| **Search** | Find a specific student by their ID |
| **Update** | Change a student's grade |
| **Remove** | Delete a student record by ID |

---

## Project Architecture

```
StudentManagementSystem_Maven/
│
├── pom.xml                          # Maven config (dependencies, Java version, build settings)
├── .gitignore                       # Files/folders excluded from Git
│
├── src/
│   ├── main/java/org/example/       # Source code
│   │   ├── Main.java                # Entry point of the application
│   │   ├── Student.java             # Student data model (POJO with validation)
│   │   └── StudentManagementSystem.java  # Business logic (CRUD operations)
│   │
│   └── test/java/org/example/       # Unit tests
│       ├── StudentTest.java         # Tests for the Student class
│       └── StudentManagementTest.java    # Tests for the management system
│
└── target/                          # (Generated) Compiled classes & packaged JAR
```

---

## Prerequisites

Before running this project, make sure you have the following installed:

| Tool | Minimum Version | Check Command |
|------|----------------|---------------|
| **Java JDK** | 23 | `java --version` |
| **Apache Maven** | 3.8+ | `mvn --version` |
| **Git** | Any | `git --version` |

### Installing Prerequisites

**Java JDK 23:**
- Download from [Oracle JDK](https://www.oracle.com/java/technologies/downloads/) or use [Eclipse Adoptium](https://adoptium.net/)
- Set `JAVA_HOME` environment variable to your JDK installation path
- Add `%JAVA_HOME%\bin` (Windows) or `$JAVA_HOME/bin` (Mac/Linux) to your system PATH

**Apache Maven:**
- Download from [https://maven.apache.org/download.cgi](https://maven.apache.org/download.cgi)
- Extract and add the `bin` folder to your system PATH
- Verify with `mvn --version`

---

## How to Clone & Run

### Step 1: Clone the Repository

```bash
git clone https://github.com/rizul1309/StudentManagementSystem_Maven.git
```

### Step 2: Navigate into the Project

```bash
cd StudentManagementSystem_Maven
```

### Step 3: Build the Project

This downloads dependencies and compiles the source code:

```bash
mvn clean compile
```

### Step 4: Run the Application

```bash
mvn exec:java -Dexec.mainClass="org.example.Main"
```

**Or** compile and run manually:

```bash
# Compile
mvn package -DskipTests

# Run the JAR
java -cp target/classes org.example.Main
```

### Expected Output

```
Hello and welcome!
i = 1
i = 2
i = 3
i = 4
i = 5
```

> **Note:** The `Main.java` currently contains a demo/placeholder. The real logic lives in `StudentManagementSystem.java` which you can call programmatically (see [How the Application Works](#how-the-application-works-end-to-end) below).

---

## File-by-File Breakdown

### 1. `pom.xml` — Maven Project Configuration

**Purpose:** This is the heart of any Maven project. It defines:
- **Project identity** (`groupId`, `artifactId`, `version`)
- **Java version** (Java 23)
- **Dependencies** (JUnit 5 for testing, Mockito for mocking)

```xml
<properties>
    <maven.compiler.source>23</maven.compiler.source>  <!-- Compile with Java 23 -->
    <maven.compiler.target>23</maven.compiler.target>  <!-- Target Java 23 bytecode -->
</properties>
```

**Dependencies explained:**
| Dependency | Purpose |
|-----------|---------|
| `junit-jupiter 5.9.2` | Modern testing framework for writing and running unit tests |
| `mockito-core 4.5.1` | Mocking library for isolating units during testing |

---

### 2. `Student.java` — The Data Model

**Purpose:** Represents a single student record. This is a **POJO** (Plain Old Java Object) with built-in validation.

**Fields:**
| Field | Type | Validation Rule |
|-------|------|----------------|
| `id` | `int` | Must be > 0 |
| `name` | `String` | Cannot be null or empty |
| `grade` | `double` | Must be between 0 and 100 |

**Key Design Decisions:**
- **Constructor validation** — Invalid data is rejected immediately at object creation time with `IllegalArgumentException`
- **Setter validation** — Even after creation, you can't set invalid values
- **No setter for ID** — Once a student is created, their ID is immutable (cannot be changed)
- **`toString()` override** — Provides a clean display format: `ID: 1 | Name: John | Grade: 85.0`

**Example Usage:**
```java
// Valid student
Student s = new Student(1, "Alice", 92.5);

// These will throw IllegalArgumentException:
new Student(-1, "Bob", 80.0);    // Invalid ID
new Student(2, "", 80.0);        // Empty name
new Student(3, "Eve", 105.0);    // Grade out of range
```

---

### 3. `StudentManagementSystem.java` — Business Logic (CRUD)

**Purpose:** Manages a collection of `Student` objects. This is where all the **Create, Read, Update, Delete** operations live.

**Internal Storage:** Uses `ArrayList<Student>` — an in-memory list (data is lost when the program exits).

**Methods:**

| Method | Returns | What It Does |
|--------|---------|--------------|
| `addStudent(id, name, grade)` | `boolean` | Adds a new student. Returns `false` if ID already exists. |
| `getAllStudents()` | `List<Student>` | Returns the full list of students. |
| `getStudentById(id)` | `Student` or `null` | Searches for a student by ID. Returns `null` if not found. |
| `updateStudentGrade(id, newGrade)` | `boolean` | Updates grade for given ID. Returns `false` if student not found. |
| `removeStudent(id)` | `boolean` | Removes student by ID. Returns `false` if not found. |
| `displayAllStudents()` | `void` | Prints all students to console, or "No students found." |

**Key Design Decisions:**
- **Duplicate prevention** — `addStudent` checks if the ID already exists before adding
- **Boolean return values** — Methods return `true`/`false` so the caller knows if the operation succeeded
- **Linear search** — `getStudentById` iterates through the list (fine for small datasets)

**Example Usage:**
```java
StudentManagementSystem sms = new StudentManagementSystem();

sms.addStudent(1, "Alice", 92.5);       // true
sms.addStudent(2, "Bob", 78.0);         // true
sms.addStudent(1, "Duplicate", 60.0);   // false (ID 1 exists)

sms.updateStudentGrade(1, 95.0);        // true
sms.removeStudent(2);                    // true

sms.displayAllStudents();
// Output: ID: 1 | Name: Alice | Grade: 95.0
```

---

### 4. `Main.java` — Application Entry Point

**Purpose:** The `main()` method is where Java starts execution. Currently contains a placeholder/demo that prints a greeting and a loop.

> In a full implementation, this would contain a menu-driven interface (Scanner-based) that lets users interact with `StudentManagementSystem` from the terminal.

---

### 5. `StudentTest.java` — Unit Tests for Student Class

**Purpose:** Verifies that the `Student` class behaves correctly in all scenarios.

**Tests covered:**
| Test | What It Verifies |
|------|-----------------|
| `testStudentCreation` | Constructor correctly assigns id, name, grade |
| `testSetValidGrade` | `setGrade()` works with valid input |
| `testSetInvalidGrade` | `setGrade()` throws exception for negative grades |
| `testSetValidName` | `setName()` works with valid input |
| `testSetInvalidName` | `setName()` throws exception for empty string |

---

### 6. `StudentManagementTest.java` — Unit Tests for CRUD Operations

**Purpose:** Verifies that the management system handles all operations correctly.

**Tests covered:**
| Test | What It Verifies |
|------|-----------------|
| `testAddStudent` | Adding a student increases the list size |
| `testAddDuplicateStudent` | Duplicate IDs are rejected |
| `testUpdateStudentGrade` | Grade updates work for existing students |
| `testUpdateNonExistingStudent` | Updating non-existent ID returns false |
| `testRemoveStudent` | Removing a student works and they're gone |
| `testRemoveNonExistingStudent` | Removing non-existent ID returns false |
| `testGetStudentById` | Searching by ID returns the correct student |
| `testGetAllStudents` | Returns all added students |

---

## How the Application Works (End-to-End)

Here's the complete flow from start to finish:

```
┌─────────────────────────────────────────────────────────┐
│                    PROGRAM FLOW                          │
├─────────────────────────────────────────────────────────┤
│                                                         │
│  1. Main.java starts execution                          │
│         │                                               │
│         ▼                                               │
│  2. StudentManagementSystem is instantiated             │
│     (creates empty ArrayList internally)                │
│         │                                               │
│         ▼                                               │
│  3. User calls addStudent(id, name, grade)              │
│         │                                               │
│         ├── Checks if ID already exists                 │
│         ├── Creates new Student object                  │
│         │      └── Student constructor validates:       │
│         │           • id > 0                            │
│         │           • name not null/empty               │
│         │           • 0 <= grade <= 100                 │
│         └── Adds to ArrayList                           │
│         │                                               │
│         ▼                                               │
│  4. User can:                                           │
│     • getAllStudents() → returns List<Student>           │
│     • getStudentById(id) → returns Student or null      │
│     • updateStudentGrade(id, grade) → true/false        │
│     • removeStudent(id) → true/false                    │
│     • displayAllStudents() → prints to console          │
│                                                         │
└─────────────────────────────────────────────────────────┘
```

### Data Lifecycle

```
Student Created → Stored in ArrayList → Queried/Updated/Removed → Lost on Program Exit
```

> **Important:** This is an **in-memory** system. All data is lost when the application stops. For persistence, you'd add a database or file storage layer.

---

## Running Tests

Run all unit tests:

```bash
mvn test
```

Run a specific test class:

```bash
mvn test -Dtest=StudentTest
mvn test -Dtest=StudentManagementTest
```

**Expected output (all tests pass):**

```
[INFO] Tests run: 5, Failures: 0, Errors: 0, Skipped: 0  (StudentTest)
[INFO] Tests run: 8, Failures: 0, Errors: 0, Skipped: 0  (StudentManagementTest)
[INFO] BUILD SUCCESS
```

---

## Build & Package

### Compile only:
```bash
mvn compile
```

### Package into a JAR:
```bash
mvn package
```
This creates `target/New_StudentManagement-1.0-SNAPSHOT.jar`

### Clean build artifacts:
```bash
mvn clean
```

### Full clean build + test:
```bash
mvn clean verify
```

---

## Project Configuration Explained

### `.gitignore`

Excludes from version control:
- `target/` — Maven's build output directory
- `.idea/` — IntelliJ IDEA project settings
- IDE-specific files (Eclipse, NetBeans, VS Code)
- OS files (`.DS_Store` on Mac)

### Maven Standard Directory Layout

```
src/main/java/    → Production source code
src/test/java/    → Test source code
target/           → Compiled output (auto-generated, not committed)
pom.xml           → Project Object Model (Maven config)
```

This is the **Maven convention** — Maven knows where to find source and test files without extra configuration.

---

## Technologies Used

| Technology | Version | Purpose |
|-----------|---------|---------|
| Java | 23 | Programming language |
| Maven | 3.8+ | Build tool & dependency management |
| JUnit 5 | 5.9.2 | Unit testing framework |
| Mockito | 4.5.1 | Mocking framework for tests |
| Git | — | Version control |

---

## Future Improvements

If you want to extend this project, here are some ideas:

- 🖥️ Add a **Scanner-based menu** in `Main.java` for interactive terminal usage
- 💾 Add **file persistence** (save/load students from a `.csv` or `.json` file)
- 🗄️ Connect to a **database** (MySQL, PostgreSQL, or SQLite with JDBC)
- 🌐 Build a **REST API** layer using Spring Boot
- 📊 Add **sorting and filtering** (by name, grade, etc.)
- 🔍 Add **search by name** (partial match)

---

## Author

**Rizul** — [GitHub Profile](https://github.com/rizul1309)

---

## License

This project is for educational purposes.
