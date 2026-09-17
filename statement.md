# PROJECT STATEMENT

## 1. PROBLEM STATEMENT
In traditional college canteen environments, manual order handling, verbal menu inquiries, and paper billing often result in long waiting queues, miscommunicated orders, inefficient tracking, and delayed status updates for students. 

To overcome these challenges without introducing heavy databases or complex software infrastructure, there is a need for a lightweight, console-driven **College Canteen Management System** built purely in Java. The system must streamline ordering for students and order management for canteen staff while serving as a comprehensive demonstration of core Java syllabus concepts.

---

## 2. OBJECTIVES
- **Automate Canteen Operations**: Provide simple digital interfaces for menu browsing, cart creation, checkout, receipt generation, and status tracking.
- **Dual User Roles**: Implement dedicated workflows for Students (ordering, cart management, tracking) and Staff (menu modification, order processing, sales analytics).
- **In-Memory Efficiency**: Use native Java collections (`ArrayList`) for fast in-memory data processing without external dependencies.
- **Multithreaded Simulation**: Use Java Threads to simulate real-time kitchen order preparation (`PENDING` -> `PREPARING` -> `READY`).
- **File Record Keeping**: Persist transaction receipts as local text files using Java File I/O.
- **Single-File Architecture**: Maintain all classes, interfaces, exceptions, and logic inside one well-structured Java file (`CanteenManagement.java`) for effortless compilation and academic presentation.

---

## 3. MAIN FEATURES
1. **Student Module**: View menu catalog, add/remove items to/from cart, calculate totals, place orders, view order history, cancel pending orders.
2. **Staff Module**: Add new food items, update existing items, remove items, view incoming orders, update order status, inspect revenue/sales summary.
3. **Billing System**: Automatic line-item total computation, cart grand total, console receipt display, and text file receipt export.
4. **Order State Lifecycle**: Manages order progression across 5 states: `PENDING`, `PREPARING`, `READY`, `COMPLETED`, and `CANCELLED`.

---

## 4. JAVA CONCEPTS DEMONSTRATED
- **Classes and Objects**: Model real-world entities (`FoodItem`, `Food`, `Beverage`, `User`, `Student`, `Staff`, `Order`, `OrderItem`, `Payment`).
- **Encapsulation**: Private state fields with controlled public getters/setters.
- **Inheritance & Polymorphism**: Hierarchy extending base classes (`FoodItem`, `User`) and polymorphic method invocations (`getType()`, `displayDetails()`, `calculateTotal()`).
- **Interfaces**: Contract enforcement via `Billable` interface.
- **Method Overloading & Overriding**: Overloaded methods for flexible calculations/displays and overridden methods for specific subclass behavior.
- **Custom Exception Handling**: Application-specific exceptions (`InvalidQuantityException`, `InvalidChoiceException`) along with robust `try-catch` blocks.
- **Collections Framework**: `ArrayList` for dynamic storage of menu items, cart items, and order history.
- **File I/O**: `FileWriter` and `BufferedWriter` to write receipts to disk.
- **Multithreading**: `OrderProcessor extends Thread` simulating asynchronous status updates.

---

## 5. BRIEF CONCLUSION
The **College Canteen Management System** successfully fulfills all operational requirements of a canteen while serving as an exemplary college-level Java project. By implementing essential Object-Oriented principles, custom exception handling, multithreading, and file management within a single source file, the project strikes an ideal balance between simplicity, functionality, and educational value.
