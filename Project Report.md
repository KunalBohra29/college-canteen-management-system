# PROJECT REPORT
## COLLEGE CANTEEN MANAGEMENT SYSTEM
### A Command-Line Application Using Core Java

| | |
|---|---|
| **Student Name** | Kunal Bohra |
| **Registration No.** | 25BAI10905 |

---

## 1. Abstract

The College Canteen Management System is a simple command-line application developed using Core Java. The system is designed to organize common canteen activities such as displaying food items, placing student orders, managing carts, calculating bills, tracking order status, and allowing staff to manage menu items and orders. The project uses object-oriented programming principles and selected Java features including collections, exception handling, file input/output, interfaces, inheritance, polymorphism, and multithreading.

## 2. Introduction

A college canteen handles many small transactions during the day. In a manual process, students may have to wait to select items, calculate totals, and check order status, while staff have to keep track of menu items and orders. This project presents a basic software solution for these activities using Java and a terminal-based interface.

## 3. Problem Statement

The objective is to develop a small and easy-to-use canteen management application that can handle food menu management, student ordering, billing, and order tracking through a command-line interface. The system should validate user input, handle common errors without terminating unexpectedly, maintain order information during execution, and demonstrate important Java programming concepts in a practical application.

## 4. Objectives

- Develop a command-line based canteen management application using Java.
- Apply classes, objects, constructors, encapsulation, inheritance and polymorphism in a practical problem.
- Use ArrayList and other basic collection features for menu, cart and order handling.
- Implement exception handling for invalid choices, quantities, food IDs and orders.
- Demonstrate Java file I/O by generating simple text receipts.
- Demonstrate basic multithreading through order-processing simulation.
- Provide separate student and staff operations in one application.

## 5. Scope of the Project

The project covers the day-to-day functional flow of a small college canteen. Students can view available food, build a cart, place orders, view orders and cancel eligible orders. Staff can add, update and remove menu items, view orders, update order status and view a simple sales summary. The application runs locally in a terminal and stores working data in memory; generated receipts may be saved as text files.

## 6. Requirements

### 6.1 Functional Requirements
- Display the canteen menu with item ID, name, category and price.
- Allow students to add and remove items from a cart.
- Calculate the order total and display a bill.
- Create an order with a unique order ID and status.
- Allow staff to manage food items and update order status.
- Display previous orders maintained during the current program execution.
- Generate a simple receipt using Java file I/O.

### 6.2 Non-Functional Requirements
- **Usability:** Menus and prompts should be clear for a first-time user.
- **Reliability:** Invalid inputs should be handled without crashing the application.
- **Maintainability:** Java classes and methods should have clear responsibilities.
- **Portability:** The program should run from a standard Java command-line environment.

## 7. Major Modules

| Module | Main Functions |
|---|---|
| Menu Management | View, add, update, remove and search food items. |
| Student Order Management | View menu, manage cart, place orders, view orders and cancel eligible orders. |
| Billing | Calculate subtotal/final amount and display or save a receipt. |
| Order Tracking | Maintain order status such as Pending, Preparing, Ready and Completed. |

## 8. Technologies and Java Concepts

| Concept | Use in Project |
|---|---|
| Classes & Objects | Represent food items, students, orders, bills and other entities. |
| Constructors | Initialize objects with required values. |
| Encapsulation | Keep important fields private and access them through methods. |
| Inheritance | Reuse common FoodItem behavior for Food and Beverage types. |
| Overloading | Provide more than one way to perform selected calculations/operations. |
| Overriding & Polymorphism | Allow subclasses to provide their own implementation of common methods. |
| Interface | Define a common payment-related operation. |
| ArrayList / Collections | Maintain menu items, cart items and orders during execution. |
| Exception Handling | Handle invalid user input and application-specific errors. |
| File I/O | Save generated order receipts as text files. |
| Multithreading | Simulate order processing and preparation. |

## 9. System Design

### 9.1 High-Level Architecture

The application follows a simple layered arrangement inside a single Java source file. The main class controls the console flow, model classes represent data, service-like methods perform menu/order/billing operations, exception classes handle invalid operations, and utility methods manage receipt files.

```
User (Student / Staff)
        ↓
Console Interface — Main menu and input handling
        ↓
Application Logic — Menu • Cart • Orders • Billing
        ↓
Java Features — ArrayList • Exceptions • I/O • Threads • OOP
```

### 9.2 Basic Workflow

1. Start application
2. Select Student or Staff
3. Perform the selected operation
4. Validate input
5. Update menu/cart/order data
6. Calculate and display results
7. Save receipt when an order is completed
8. Return to the appropriate menu or exit

### 9.3 Main Classes

| Class | Responsibility |
|---|---|
| CanteenManagement | Main class; starts the application and controls menus. |
| FoodItem | Stores common food-item information such as ID, name and price. |
| Food | Specialized food item demonstrating inheritance. |
| Beverage | Specialized beverage item demonstrating inheritance. |
| Student | Stores student information and order-related details. |
| Staff | Represents canteen staff operations. |
| OrderItem | Stores an item and its quantity inside an order/cart. |
| Order | Maintains order ID, items, amount and status. |
| Payment | Interface for payment operation. |
| OrderProcessor | Thread used for simple order-processing simulation. |
| Custom Exceptions | Handle invalid quantities, choices or other application errors. |

## 10. Sample Command-Line Interface

```
========================================
COLLEGE CANTEEN MANAGEMENT SYSTEM
========================================
1. Student
2. Staff
3. Exit
Enter choice:

========== STUDENT MENU ==========
1. View Menu
2. Add Item to Cart
3. Remove Item from Cart
4. View Cart
5. Place Order
6. View Previous Orders
7. Cancel Order
8. Logout
```

## 11. Testing

| Test Case | Input / Situation | Expected Result |
|---|---|---|
| Valid menu selection | Select Student/Staff | Corresponding menu is displayed. |
| Invalid choice | Enter an unsupported menu number | Error message and retry. |
| Invalid food ID | Enter a non-existing ID | Food-not-found message. |
| Invalid quantity | Enter 0 or negative quantity | Quantity validation error. |
| Empty cart | Try to place an order with no items | Order is not placed. |
| Valid order | Add items and confirm | Order ID and total are displayed. |
| Cancel order | Cancel a pending order | Order becomes cancelled. |
| Receipt generation | Complete valid order | Text receipt is generated. |

## 12. Installation and Execution

The project runs from a terminal using a standard Java Development Kit. No GUI environment, database server or external programming language is required.

**Compile:**
```
javac CanteenManagement.java
```

**Run:**
```
java CanteenManagement
```

## 13. Limitations

- The application is command-line based and does not provide a graphical interface.
- Working data is maintained in memory during execution rather than in a persistent database.
- Does not implement online payments or network-based ordering.

## 14. Future Enhancements

- Add a persistent database using JDBC.
- Add user authentication and role-based access.
- Add a graphical or web interface as a separate future version.
- Add daily/monthly sales reports.
- Add inventory and stock-level management.

## 15. Conclusion

The College Canteen Management System provides a compact practical example of applying Core Java concepts to a realistic college problem. The project combines object-oriented design with collections, exception handling, file operations and basic multithreading in a command-line application. Its small scope makes the implementation easy to test, understand and demonstrate as part of a Programming in Java course evaluation.

## 16. Course Alignment

The CSE2006 syllabus identifies Java fundamentals and flow control, object-oriented programming, exception handling, multithreading, collections and Java I/O among its core units. The project uses these areas directly, reflecting indicative experiments involving constructors, classes, overloading, inheritance, method overriding, polymorphism, interfaces, multithreading and I/O.
