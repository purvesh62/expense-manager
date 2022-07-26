package com.expensify.database;

import java.sql.*;
import java.util.List;

public class MySqlDatabase implements  IDatabase{
    private static MySqlDatabase instance;
    private static String dbHost;
    private static String dbName;
    private static String dbPort;
    private static String dbUrl;
    private static String dbUser;
    private static String dbPassword;
    private Connection conn;

    private MySqlDatabase() {
        dbHost = System.getenv("DB_HOST");
        dbName = System.getenv("DB_NAME");
        dbPort = System.getenv("DB_PORT");
        dbUser = System.getenv("DB_USER");
        dbPassword = System.getenv("DB_PASSWORD");
        dbUrl = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;
    }

    public static MySqlDatabase instance() {
        if(MySqlDatabase.instance == null){
            MySqlDatabase.instance = new MySqlDatabase();
        }
        return MySqlDatabase.instance;
    }

    @Override
    public Connection connectDB() {
        try {
            conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            if (conn != null) {
                return conn;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    @Override
    public ResultSet executeProcedure(String procQuery, List<Object> parameterList) {
        ResultSet resultSet = null;
        try {
            conn = connectDB();
            CallableStatement statement = conn.prepareCall("{" + procQuery + "}");
            for(int i= 0;i<parameterList.size();i++) {
                statement.setObject(i+1,parameterList.get(i));
            }
            boolean result = statement.execute();
            if (result) {
                resultSet = statement.getResultSet();
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return resultSet;
    }
    @Override
    public void closeConnection() throws SQLException {
        if(!conn.isClosed()){
            conn.close();
        }
    }
}
