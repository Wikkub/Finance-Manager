package com.financemanager;

import com.financemanager.DbInitializer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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

            boolean isProgramRunning = true;
            while (isProgramRunning) {
                optionMenu();
                String chosenOption = SCANNER.nextLine();
                try {
                    final int option = Integer.parseInt(chosenOption);
                    switch (option) {
                        case 1:
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
        System.out.println("2 - Show categories");
    }
}
