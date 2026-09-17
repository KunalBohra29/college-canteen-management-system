# COLLEGE CANTEEN MANAGEMENT SYSTEM

A complete, lightweight, student-level Java console application designed to manage canteen operations for students and canteen staff. The entire system is built in pure Java using a single source file without any external dependencies or database overhead.

---

## 📌 PROJECT DESCRIPTION

The **College Canteen Management System** is a command-line driven application created for college students to demonstrate foundational and advanced Object-Oriented Programming (OOP) concepts in Java. It allows students to browse the food menu, add/remove items to/from a shopping cart, place orders, view order status updates, and cancel pending orders. Canteen staff can manage food items (Add, Update, Remove), process orders, update order status, and view daily sales analytics.

---

## ✨ KEY FEATURES

### 🎓 Student Portal
1. **View Menu**: Browse default and updated food and beverage items with category and pricing details.
2. **Add Item to Cart**: Select items by ID and specify quantity with automatic subtotal computation.
3. **Remove Item from Cart**: Modify item quantity or remove items from cart before checkout.
4. **View Cart**: View itemized breakdown and total cost of selected items.
5. **Place Order**: Complete checkout, generate an automated bill, trigger File I/O receipt generation, and launch background thread processing.
6. **View Previous Orders**: Track previous orders and their live status (`PENDING`, `PREPARING`, `READY`, `COMPLETED`, `CANCELLED`).
7. **Cancel Pending Order**: Cancel orders that are still in `PENDING` state.

### 👨‍🍳 Staff Portal
1. **View Menu**: Inspect complete canteen catalog.
2. **Add Food Item**: Dynamically add new Food or Beverage items to the menu.
3. **Update Food Item**: Modify item names or prices.
4. **Remove Food Item**: Delete outdated items from menu catalog.
5. **View Orders**: View list of all student orders.
6. **Update Order Status**: Change status across `PENDING`, `PREPARING`, `READY`, `COMPLETED`, and `CANCELLED`.
7. **View Sales Summary**: View total orders count, status breakdown, and overall revenue.

---

## ☕ JAVA SYLLABUS CONCEPTS DEMONSTRATED

| Java Concept | Implementation Details in `CanteenManagement.java` |
| :--- | :--- |
| **Classes & Objects** | Realized through `FoodItem`, `Food`, `Beverage`, `User`, `Student`, `Staff`, `Order`, `OrderItem`, `Payment`, and `OrderProcessor`. |
| **Constructors & `super`** | Parameterized constructors in base and derived classes passing fields using `super()`. |
| **Encapsulation** | Private fields (`id`, `name`, `price`, `quantity`, `status`) accessed via public getters and setters. |
| **Inheritance** | `Food` and `Beverage` extend abstract `FoodItem`; `Student` and `Staff` extend abstract `User`. |
| **Method Overloading** | Overloaded methods like `displayDetails()`, `getItemTotal()`, and `calculateTotal()`. |
| **Method Overriding** | `@Override` implementation of `getType()` in subclasses and `showMenu()` in user roles. |
| **Polymorphism** | Processing `ArrayList<FoodItem>` containing mixed `Food` and `Beverage` instances dynamically. |
| **Interfaces** | `Billable` interface implemented by `Order` defining `calculateTotal()` and `generateBill()`. |
| **Collections (ArrayList)** | Dynamic memory management for `menuList`, `currentCart`, and `orderList`. |
| **Exception Handling** | `try-catch-finally` handling `NumberFormatException`, `InputMismatchException`, `IOException`. |
| **Custom Exceptions** | `InvalidQuantityException` and `InvalidChoiceException` for input validation. |
| **File I/O** | `BufferedWriter` and `FileWriter` creating text receipts (`receipt_1001.txt`). |
| **Multithreading** | `OrderProcessor` extending `Thread` to asynchronously update order state (`PENDING` -> `PREPARING` -> `READY`) with `Thread.sleep()`. |

---

## ⚙️ REQUIREMENTS

- **Operating System**: Windows / Linux / macOS
- **Java Development Kit (JDK)**: JDK 8 or higher installed and configured in System PATH
- **Terminal / Command Prompt / PowerShell**

---

## 🚀 COMPILATION AND RUN INSTRUCTIONS

### 1. Compile the Project
Open terminal in the project directory and run:
```bash
javac CanteenManagement.java
```

### 2. Run the Application
```bash
java CanteenManagement
```

---

## 💻 EXAMPLE USAGE SESSION

```text
========================================
     COLLEGE CANTEEN MANAGEMENT
========================================
1. Student
2. Staff
3. Exit
Enter choice: 1

========== STUDENT MENU ==========
1. View Menu
2. Add Item to Cart
3. Remove Item from Cart
4. View Cart
5. Place Order
6. View Previous Orders
7. Cancel Order
8. Logout
Enter choice: 1

-------------------------------------------------------------
                    CANTEEN MENU                             
-------------------------------------------------------------
ID   | Item Name        | Category / Type    | Price   
-------------------------------------------------------------
1    | Samosa           | Food [Snacks]      | Rs. 15.00 
2    | Aloo Paratha     | Food [Main Course] | Rs. 40.00 
3    | Maggi            | Food [Fast Food]   | Rs. 50.00 
...
```

---

## 📁 PROJECT STRUCTURE

```text
JAVA PROJECT/
├── CanteenManagement.java    # Single source file containing all logic & classes
├── README.md                # Project documentation and guide
└── statement.md             # Problem statement, objectives & conclusion
```
