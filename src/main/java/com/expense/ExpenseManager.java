package com.expense;
import java.util.*;
class Expense {
    String category;
    double amount;

    Expense(String category, double amount) {
        this.category = category;
        this.amount = amount;
    }
}
public class ExpenseManager {

    private List<Expense> expenses = new ArrayList<>();

    public void addExpense(String category, double amount) {
        expenses.add(new Expense(category, amount));
    }

    public double getTotalExpense() {
        double total = 0;
        for (Expense e : expenses) {
            total += e.amount;
        }
        return total;
    }

    public double getExpenseByCategory(String category) {
        double total = 0;
        for (Expense e : expenses) {
            if (e.category.equalsIgnoreCase(category)) {
                total += e.amount;
            }
        }
        return total;
    }
    public void clearExpenses() {
        expenses.clear();
    }
    public static void main(String[] args) {
        ExpenseManager manager = new ExpenseManager();
        manager.addExpense("Food", 200);
        manager.addExpense("Travel", 300);

        System.out.println("Total Expense: " + manager.getTotalExpense());
    }
}