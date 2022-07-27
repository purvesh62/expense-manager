package com.expensify.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface IDatabase {
    Connection connectDB();

    ResultSet executeProcedure(String procQuery, List<Object> parameterList) throws SQLException;

    void closeConnection() throws SQLException;
}
