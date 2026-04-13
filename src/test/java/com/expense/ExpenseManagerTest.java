package com.expense;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ExpenseManagerTest {

    @Test
    void testTotalExpense() {
        ExpenseManager manager = new ExpenseManager();
        manager.addExpense("Food", 200);
        manager.addExpense("Travel", 300);

        assertEquals(500, manager.getTotalExpense());
    }

    @Test
    void testCategoryExpense() {
        ExpenseManager manager = new ExpenseManager();
        manager.addExpense("Food", 200);
        manager.addExpense("Food", 100);

        assertEquals(300, manager.getExpenseByCategory("Food"));
    }

    @Test
    void testClearExpenses() {
        ExpenseManager manager = new ExpenseManager();
        manager.addExpense("Food", 200);
        manager.clearExpenses();

        assertEquals(0, manager.getTotalExpense());
    }
}