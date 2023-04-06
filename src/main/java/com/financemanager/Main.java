package com.financemanager;

import com.financemanager.DbInitializer;
import com.financemanager.entity.Category;
import com.financemanager.entity.Expense;
import com.financemanager.entity.Income;
import com.financemanager.repository.CategoryRepository;
import com.financemanager.repository.IncomeRepository;
import com.financemanager.service.CategoryServices;
import com.financemanager.service.IncomeServices;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;

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
                            categoryRepository.findAll();
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
    }
}
