package com.expensify.database;

import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.List;

@Component
public class MySqlDatabaseManager implements IDatabase {

    private final String dbURL = "jdbc:mysql://db-5308.cs.dal.ca/CSCI5308_7_DEVINT";
    private final String username = "CSCI5308_7_DEVINT_USER";
    private final String password = "4KhAVapdN5";

    private Connection conn = null;
    public MySqlDatabaseManager(){

    }
    @Override
    public Connection connectDB() {
        try {
             conn = DriverManager.getConnection(dbURL, username, password);
            if (conn != null) {
                return conn;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public ResultSet executeProcedure(String procQuery, List<Object> parameterList) throws SQLException {
        ResultSet resultSet = null;
        try{
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
