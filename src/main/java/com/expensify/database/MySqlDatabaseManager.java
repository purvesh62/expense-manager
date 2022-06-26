package com.expensify.database;

import java.sql.*;

public class MySqlDatabaseManager implements IDatabase {

    private final String dbURL = "jdbc:mysql://localhost:3306/CSCI5308_7_DEVINT";
    private final String username = "root";
    private final String password = "root";

    @Override
    public Connection connectDB() {
        try {
            Connection conn = DriverManager.getConnection(dbURL, username, password);

            if (conn != null) {
                return conn;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public ResultSet executeProcedure(String procQuery) {
        ResultSet resultSet = null;
        try (Connection conn = connectDB()) {
            CallableStatement statement = conn.prepareCall("{" + procQuery + "}");
            statement.setString(1, "purvesh@gmail.com");
            boolean result = statement.execute();
            if (result) {
                resultSet = statement.getResultSet();
            }
            System.out.println("Executed");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return resultSet;
    }
}
