package com.expensify;


import com.expensify.database.IDatabase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DatabaseMock implements IDatabase {
    @Override
    public Connection connectDB() {
        return null;
    }

    @Override
    public ResultSet executeProcedure(String procQuery, List<Object> parameterList) throws SQLException {
        return null;
    }

    @Override
    public void closeConnection() throws SQLException {

    }
}
