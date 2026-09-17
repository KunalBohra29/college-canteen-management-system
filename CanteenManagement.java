import java.io.*;
import java.util.*;

class InvalidQuantityException extends Exception { public InvalidQuantityException(String m) { super(m); } }
class InvalidChoiceException extends Exception { public InvalidChoiceException(String m) { super(m); } }

interface Billable { double calculateTotal(); void generateBill(); }

abstract class FoodItem {
    private int id; private String name; private double price;
    public FoodItem(int id, String name, double price) { this.id = id; this.name = name; this.price = price; }
    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public void setName(String name) { this.name = name; }
    public void setPrice(double price) { this.price = price; }
    public abstract String getType();
    public void displayDetails() { System.out.printf("%-3d | %-15s | %-18s | Rs.%.2f\n", id, name, getType(), price); }
    public void displayDetails(boolean concise) {
        if (concise) System.out.println("#" + id + " " + name + " (Rs." + price + ")"); else displayDetails();
    }
}

class Food extends FoodItem {
    private String category;
    public Food(int id, String name, double price, String category) { super(id, name, price); this.category = category; }
    @Override public String getType() { return "Food [" + category + "]"; }
}

class Beverage extends FoodItem {
    private String serving;
    public Beverage(int id, String name, double price, String serving) { super(id, name, price); this.serving = serving; }
    @Override public String getType() { return "Beverage [" + serving + "]"; }
}

class OrderItem {
    private FoodItem item; private int quantity;
    public OrderItem(FoodItem item, int quantity) throws InvalidQuantityException {
        if (quantity <= 0) throw new InvalidQuantityException("Quantity must be > 0.");
        this.item = item; this.quantity = quantity;
    }
    public FoodItem getItem() { return item; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) throws InvalidQuantityException {
        if (quantity <= 0) throw new InvalidQuantityException("Quantity must be > 0.");
        this.quantity = quantity;
    }
    public double getItemTotal() { return item.getPrice() * quantity; }
    public double getItemTotal(double discountPercent) { return getItemTotal() * (1 - discountPercent / 100.0); }
}

class Order implements Billable {
    private int orderId; private String customer;
    private ArrayList<OrderItem> items = new ArrayList<>();
    private String status = "PENDING";

    public Order(int orderId, String customer, ArrayList<OrderItem> cart) {
        this.orderId = orderId; this.customer = customer;
        for (OrderItem oi : cart) { try { items.add(new OrderItem(oi.getItem(), oi.getQuantity())); } catch (Exception ignored) {} }
    }
    public int getOrderId() { return orderId; }
    public String getCustomer() { return customer; }
    public ArrayList<OrderItem> getItems() { return items; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override public double calculateTotal() { double total = 0; for (OrderItem oi : items) total += oi.getItemTotal(); return total; }
    public double calculateTotal(double taxRate) { return calculateTotal() * (1 + taxRate / 100.0); }

    @Override public void generateBill() {
        System.out.println("\n========== CANTEEN RECEIPT (#" + orderId + ") ==========\nCustomer: " + customer + " | Status: " + status + "\n----------------------------------------");
        for (OrderItem oi : items) System.out.printf("%-16s x%-2d  Rs.%.2f\n", oi.getItem().getName(), oi.getQuantity(), oi.getItemTotal());
        System.out.printf("----------------------------------------\nGrand Total: Rs.%.2f\n========================================\n", calculateTotal());
    }

    public void saveReceiptToFile() {
        String filename = "receipt_" + orderId + ".txt";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            bw.write("Order #" + orderId + " | Customer: " + customer + " | Status: " + status + "\n----------------------------------------\n");
            for (OrderItem oi : items) bw.write(oi.getItem().getName() + " x" + oi.getQuantity() + " = Rs." + oi.getItemTotal() + "\n");
            bw.write("----------------------------------------\nGrand Total: Rs." + calculateTotal() + "\n");
            System.out.println("[File I/O] Saved " + filename);
        } catch (IOException e) { System.out.println("[File Error] " + e.getMessage()); }
    }
}

class Payment {
    public static void processPayment(double amount) { System.out.printf("Payment of Rs.%.2f processed successfully!\n", amount); }
}

class OrderProcessor extends Thread {
    private Order order;
    public OrderProcessor(Order order) { this.order = order; }
    @Override public void run() {
        try {
            Thread.sleep(3000);
            if (!order.getStatus().equals("CANCELLED")) { order.setStatus("PREPARING"); System.out.println("\n[KITCHEN THREAD] Order #" + order.getOrderId() + " is PREPARING."); }
            Thread.sleep(4000);
            if (!order.getStatus().equals("CANCELLED")) { order.setStatus("READY"); System.out.println("\n[KITCHEN THREAD] Order #" + order.getOrderId() + " is READY for pickup!"); }
        } catch (InterruptedException ignored) {}
    }
}

abstract class User {
    private String name, role;
    public User(String name, String role) { this.name = name; this.role = role; }
    public String getName() { return name; }
    public abstract void showMenu();
}

class Student extends User {
    public Student(String name) { super(name, "Student"); }
    @Override public void showMenu() {
        System.out.print("\n========== STUDENT MENU ==========\n1. View Menu\n2. Add Item to Cart\n3. Remove Item from Cart\n4. View Cart\n5. Place Order\n6. View Previous Orders\n7. Cancel Order\n8. Logout\nEnter choice: ");
    }
}

class Staff extends User {
    public Staff(String name) { super(name, "Staff"); }
    @Override public void showMenu() {
        System.out.print("\n========== STAFF MENU ==========\n1. View Menu\n2. Add Food Item\n3. Update Food Item\n4. Remove Food Item\n5. View Orders\n6. Update Order Status\n7. View Sales\n8. Logout\nEnter choice: ");
    }
}

public class CanteenManagement {
    private static ArrayList<FoodItem> menuList = new ArrayList<>();
    private static ArrayList<Order> orderList = new ArrayList<>();
    private static ArrayList<OrderItem> currentCart = new ArrayList<>();
    private static int nextOrderId = 1001;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        loadDefaultMenu();
        while (true) {
            System.out.print("\n========================================\n     COLLEGE CANTEEN MANAGEMENT\n========================================\n1. Student\n2. Staff\n3. Exit\nEnter choice: ");
            try {
                int c = getIntInput();
                if (c == 1) handleStudentFlow();
                else if (c == 2) handleStaffFlow();
                else if (c == 3) { System.out.println("\nThank you for using College Canteen Management System!"); break; }
                else throw new InvalidChoiceException("Choice must be 1, 2, or 3.");
            } catch (InvalidChoiceException e) { System.out.println("[Error] " + e.getMessage()); }
        }
    }

    private static void loadDefaultMenu() {
        menuList.add(new Food(1, "Samosa", 15.0, "Snacks")); menuList.add(new Food(2, "Aloo Paratha", 40.0, "Main Course"));
        menuList.add(new Food(3, "Maggi", 50.0, "Fast Food")); menuList.add(new Food(4, "Sandwich", 45.0, "Fast Food"));
        menuList.add(new Food(5, "Burger", 70.0, "Fast Food")); menuList.add(new Food(6, "Momos", 60.0, "Fast Food"));
        menuList.add(new Beverage(7, "Tea", 15.0, "Hot")); menuList.add(new Beverage(8, "Coffee", 25.0, "Hot"));
        menuList.add(new Beverage(9, "Cold Coffee", 50.0, "Cold")); menuList.add(new Beverage(10, "Water", 10.0, "Cold"));
    }

    private static int getIntInput() {
        while (true) { try { return Integer.parseInt(scanner.nextLine().trim()); } catch (Exception e) { System.out.print("Invalid input! Enter number: "); } }
    }

    private static double getDoubleInput() {
        while (true) { try { return Double.parseDouble(scanner.nextLine().trim()); } catch (Exception e) { System.out.print("Invalid input! Enter price: "); } }
    }

    private static void displayMenu() {
        System.out.println("\n-------------------------------------------------------------\nID  | Item Name       | Category / Type    | Price\n-------------------------------------------------------------");
        for (FoodItem item : menuList) item.displayDetails();
        System.out.println("-------------------------------------------------------------");
    }

    private static FoodItem findFoodItemById(int id) {
        for (FoodItem fi : menuList) if (fi.getId() == id) return fi;
        return null;
    }

    private static Order findOrderById(int id) {
        for (Order o : orderList) if (o.getOrderId() == id) return o;
        return null;
    }

    private static void handleStudentFlow() {
        Student student = new Student("Student");
        while (true) {
            student.showMenu();
            try {
                int ch = getIntInput();
                if (ch == 1) displayMenu();
                else if (ch == 2) addItemToCart();
                else if (ch == 3) removeItemFromCart();
                else if (ch == 4) viewCart();
                else if (ch == 5) placeOrder();
                else if (ch == 6) viewPreviousOrders();
                else if (ch == 7) cancelPendingOrder();
                else if (ch == 8) { System.out.println("Logging out from Student Portal..."); break; }
                else throw new InvalidChoiceException("Select choice between 1 and 8.");
            } catch (Exception e) { System.out.println("[Error] " + e.getMessage()); }
        }
    }

    private static void addItemToCart() throws Exception {
        displayMenu(); System.out.print("Enter Food ID: ");
        FoodItem item = findFoodItemById(getIntInput());
        if (item == null) throw new InvalidChoiceException("Food item ID not found.");
        System.out.print("Enter quantity: ");
        int qty = getIntInput();
        if (qty <= 0) throw new InvalidQuantityException("Quantity must be > 0.");
        for (OrderItem oi : currentCart) {
            if (oi.getItem().getId() == item.getId()) {
                oi.setQuantity(oi.getQuantity() + qty); System.out.println("Updated cart quantity!"); return;
            }
        }
        currentCart.add(new OrderItem(item, qty)); System.out.println("Added " + item.getName() + " to cart!");
    }

    private static void removeItemFromCart() throws Exception {
        if (currentCart.isEmpty()) { System.out.println("Cart is empty."); return; }
        viewCart(); System.out.print("Enter Food ID to remove: ");
        int id = getIntInput(); OrderItem target = null;
        for (OrderItem oi : currentCart) if (oi.getItem().getId() == id) { target = oi; break; }
        if (target == null) throw new InvalidChoiceException("Item not in cart.");
        System.out.print("Enter quantity to remove: ");
        int qty = getIntInput();
        if (qty >= target.getQuantity()) currentCart.remove(target);
        else target.setQuantity(target.getQuantity() - qty);
        System.out.println("Cart updated!");
    }

    private static void viewCart() {
        if (currentCart.isEmpty()) { System.out.println("Cart is empty."); return; }
        System.out.println("\n------------------- YOUR CART -------------------");
        double total = 0;
        for (OrderItem oi : currentCart) {
            System.out.printf("#%-3d %-15s x%-2d  Rs.%.2f\n", oi.getItem().getId(), oi.getItem().getName(), oi.getQuantity(), oi.getItemTotal());
            total += oi.getItemTotal();
        }
        System.out.printf("Subtotal: Rs.%.2f\n-------------------------------------------------\n", total);
    }

    private static void placeOrder() {
        if (currentCart.isEmpty()) { System.out.println("Cart is empty! Cannot place order."); return; }
        viewCart(); double total = 0;
        for (OrderItem oi : currentCart) total += oi.getItemTotal();
        Payment.processPayment(total);
        Order order = new Order(nextOrderId++, "Student", currentCart);
        orderList.add(order); order.generateBill(); order.saveReceiptToFile();
        new OrderProcessor(order).start(); currentCart.clear();
        System.out.println("Order #" + order.getOrderId() + " placed successfully!");
    }

    private static void viewPreviousOrders() {
        if (orderList.isEmpty()) { System.out.println("No previous orders found."); return; }
        System.out.println("\n------------------ ORDER HISTORY ------------------");
        for (Order o : orderList) {
            System.out.printf("Order #%-4d | Status: %-10s | Total: Rs.%.2f\n", o.getOrderId(), o.getStatus(), o.calculateTotal());
            for (OrderItem oi : o.getItems()) System.out.println("  - " + oi.getItem().getName() + " x" + oi.getQuantity());
        }
    }

    private static void cancelPendingOrder() throws Exception {
        if (orderList.isEmpty()) { System.out.println("No orders available."); return; }
        viewPreviousOrders(); System.out.print("Enter Order ID to cancel: ");
        Order o = findOrderById(getIntInput());
        if (o == null) throw new InvalidChoiceException("Order not found.");
        if ("PENDING".equals(o.getStatus())) {
            o.setStatus("CANCELLED"); System.out.println("Order #" + o.getOrderId() + " CANCELLED.");
        } else System.out.println("Cannot cancel order with status: " + o.getStatus());
    }

    private static void handleStaffFlow() {
        Staff staff = new Staff("Admin");
        while (true) {
            staff.showMenu();
            try {
                int ch = getIntInput();
                if (ch == 1) displayMenu();
                else if (ch == 2) addFoodItem();
                else if (ch == 3) updateFoodItem();
                else if (ch == 4) removeFoodItem();
                else if (ch == 5) viewPreviousOrders();
                else if (ch == 6) updateOrderStatus();
                else if (ch == 7) viewSalesSummary();
                else if (ch == 8) { System.out.println("Logging out from Staff Portal..."); break; }
                else throw new InvalidChoiceException("Select choice between 1 and 8.");
            } catch (Exception e) { System.out.println("[Error] " + e.getMessage()); }
        }
    }

    private static void addFoodItem() throws Exception {
        System.out.print("Enter Name: "); String name = scanner.nextLine().trim();
        System.out.print("Enter Price: "); double price = getDoubleInput();
        System.out.print("Type (1. Food, 2. Beverage): "); int type = getIntInput();
        int maxId = 0; for (FoodItem fi : menuList) if (fi.getId() > maxId) maxId = fi.getId();
        int id = maxId + 1;
        if (type == 1) {
            System.out.print("Enter Category: "); menuList.add(new Food(id, name, price, scanner.nextLine().trim()));
        } else if (type == 2) {
            System.out.print("Enter Serving Type: "); menuList.add(new Beverage(id, name, price, scanner.nextLine().trim()));
        } else throw new InvalidChoiceException("Invalid item type.");
        System.out.println("Added item #" + id + " (" + name + ")");
    }

    private static void updateFoodItem() throws Exception {
        displayMenu(); System.out.print("Enter Food ID: "); FoodItem item = findFoodItemById(getIntInput());
        if (item == null) throw new InvalidChoiceException("Item not found.");
        System.out.print("New Name (leave blank to keep): "); String name = scanner.nextLine().trim();
        if (!name.isEmpty()) item.setName(name);
        System.out.print("New Price (0 to keep): "); double p = getDoubleInput();
        if (p > 0) item.setPrice(p);
        System.out.println("Item #" + item.getId() + " updated!");
    }

    private static void removeFoodItem() throws Exception {
        displayMenu(); System.out.print("Enter Food ID to remove: "); FoodItem item = findFoodItemById(getIntInput());
        if (item == null) throw new InvalidChoiceException("Item not found.");
        menuList.remove(item); System.out.println("Removed item #" + item.getId());
    }

    private static void updateOrderStatus() throws Exception {
        viewPreviousOrders(); System.out.print("Enter Order ID: "); Order o = findOrderById(getIntInput());
        if (o == null) throw new InvalidChoiceException("Order not found.");
        System.out.println("Select Status: 1.PENDING 2.PREPARING 3.READY 4.COMPLETED 5.CANCELLED");
        int st = getIntInput(); String[] statuses = {"PENDING", "PREPARING", "READY", "COMPLETED", "CANCELLED"};
        if (st >= 1 && st <= 5) o.setStatus(statuses[st - 1]);
        else throw new InvalidChoiceException("Invalid status selection.");
        System.out.println("Order #" + o.getOrderId() + " status updated to " + o.getStatus());
    }

    private static void viewSalesSummary() {
        if (orderList.isEmpty()) { System.out.println("No sales recorded."); return; }
        double totalRev = 0; int completed = 0;
        for (Order o : orderList) {
            if ("COMPLETED".equals(o.getStatus())) { completed++; totalRev += o.calculateTotal(); }
            else if (!"CANCELLED".equals(o.getStatus())) { totalRev += o.calculateTotal(); }
        }
        System.out.println("\n----------------- SALES SUMMARY -----------------\nTotal Orders Placed : " + orderList.size() + "\nCompleted Orders    : " + completed);
        System.out.printf("Total Revenue       : Rs.%.2f\n-------------------------------------------------\n", totalRev);
    }
}
