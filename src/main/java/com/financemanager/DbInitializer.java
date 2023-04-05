package com.financemanager;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DbInitializer {

    private final Connection connection;
    public DbInitializer(Connection connection) {
        this.connection = connection;
    }

    public void initDb() throws IOException, SQLException {
        try (InputStream category = getClass().getResourceAsStream("/sql/category_ddl.sql");
             InputStream expense = getClass().getResourceAsStream("/sql/expense_ddl.sql");
             InputStream income = getClass().getResourceAsStream("/sql/income_ddl.sql")) {

            executeSqlFromResource(category);
            executeSqlFromResource(expense);
            executeSqlFromResource(income);
        }
    }

    private void executeSqlFromResource(InputStream inputStream) throws IOException, SQLException {
        if (inputStream == null) {
            System.err.println("Failed to open resource");
            return;
        }
        String sql = new String(inputStream.readAllBytes());
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.execute();

    }
}