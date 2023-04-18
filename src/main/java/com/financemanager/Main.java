package com.financemanager;

import com.financemanager.entity.Expense;
import com.financemanager.entity.Income;
import com.financemanager.repository.CategoryRepository;
import com.financemanager.repository.ExpenseRepository;
import com.financemanager.repository.IncomeRepository;
import com.financemanager.service.CategoryServices;
import com.financemanager.service.ExpenseServices;
import com.financemanager.service.IncomeServices;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static final String JDBC_URL = "jdbc:mysql://localhost:3306/";
    private static final String DB_NAME_ENV = "DB_NAME";
    private static final String DB_USER_ENV = "DB_USER";
    private static final String DB_PASSWORD_ENV = "DB_PASSWORD";


    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) throws SQLException, IOException {

        try (final Connection connection = DriverManager.getConnection(JDBC_URL + System.getenv(DB_NAME_ENV),
                System.getenv(DB_USER_ENV), System.getenv(DB_PASSWORD_ENV))) {

            DbInitializer dbInitializer = new DbInitializer(connection);
            dbInitializer.initDb();

            CategoryRepository categoryRepository = new CategoryRepository();
            CategoryServices categoryServices = new CategoryServices(categoryRepository);
            IncomeRepository incomeRepository = new IncomeRepository();
            IncomeServices incomeServices = new IncomeServices(incomeRepository);
            ExpenseRepository expenseRepository = new ExpenseRepository();
            ExpenseServices expenseServices = new ExpenseServices(expenseRepository);


            boolean isProgramRunning = true;
            while (isProgramRunning) {
                optionMenu();
                String chosenOption = SCANNER.nextLine();
                try {
                    final int option = Integer.parseInt(chosenOption);
                    switch (option) {
                        case 1:
                            System.out.println("Provide new category");
                            String categoryName = SCANNER.nextLine();
                            categoryServices.addCategory(categoryName);
                            break;
                        case 2:
                            System.out.println("Provide category id to delete");
                            String categoryId = SCANNER.nextLine();
                            categoryServices.deleteCategory(categoryId);
                        case 3:
                            System.out.println(categoryServices.findAll());
                            break;
                        case 4:
                            System.out.println("Provide amount");
                            int amount = SCANNER.nextInt();
                            System.out.println("Provide comment");
                            SCANNER.nextLine();
                            String comment = SCANNER.nextLine();
                            incomeServices.addIncome(new Income(amount, LocalDate.now(), comment));
                            break;
                        case 5:
                            System.out.println("Provide income id to delete");
                            String incomeId = SCANNER.nextLine();
                            incomeServices.deleteIncomeById(incomeId);
                            break;
                        case 6:
                            System.out.println(incomeServices.findAll());
                            break;
                        case 7:
                            System.out.println("Provide expense amount");
                            int expenseAmount = SCANNER.nextInt();
                            SCANNER.nextLine();
                            System.out.println("Provide category id");
                            String expenseCategoryId = SCANNER.nextLine();
                            categoryRepository.findById(expenseCategoryId);
                            System.out.println("Provide comment");
                            String expenseComment = SCANNER.nextLine();
                            expenseServices.addExpense(new Expense(expenseAmount, categoryRepository.findById(expenseCategoryId), LocalDate.now(), expenseComment));
                            break;
                        case 8:
                            System.out.println("Provide expense id to delete");
                            String expenseIdToDelete = SCANNER.nextLine();
                            expenseServices.deleteExpenseById(expenseIdToDelete);
                            break;
                        case 9:
                            System.out.println(expenseServices.findAllExpenses());
                            break;
                        case 10:
                            System.out.println(categoryServices.findAll());
                            System.out.println("Provide category id");
                            String chosenCategory = SCANNER.nextLine();
                            System.out.println(expenseServices.findExpensesByCategoryId(chosenCategory));
                            break;
                        case 11:
                            System.out.println("Expenses sum = " + expenseRepository.actualExpensesBalance());
                            System.out.println("Incomes sum = " + incomeRepository.actualIncomesBalance());
                            break;
                    }

                } catch (NumberFormatException e) {
                    System.err.printf(chosenOption + "is invalid option");
                }
            }
        }
    }

    public static void optionMenu() {
        System.out.println("0 - Exit");
        System.out.println("1 - Add new category");
        System.out.println("2 - Delete category");
        System.out.println("3 - Show categories");
        System.out.println("4 - Add new income");
        System.out.println("5 - Delete income");
        System.out.println("6 - Show incomes");
        System.out.println("7 - Add new expense");
        System.out.println("8 - Delete expense");
        System.out.println("9 - Show all expenses");
        System.out.println("10 - Show expenses by category");
        System.out.println("11 - Show balance");
    }
}
