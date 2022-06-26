package com.expensify.database;

import java.sql.Connection;
import java.sql.ResultSet;

public interface IDatabase {
    Connection connectDB();

    ResultSet executeProcedure(String procQuery);
}
