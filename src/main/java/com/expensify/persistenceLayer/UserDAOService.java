package com.expensify.persistenceLayer;

import com.expensify.database.IDatabase;
import com.expensify.database.MySqlDatabase;
import com.expensify.model.IUser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UserDAOService implements IUserDAOService {
    private IDatabase database;

    public UserDAOService(IDatabase database) {
        this.database = MySqlDatabase.instance();
    }
    @Override
    public int saveUser(String firstName, String lastName, String email, String password, String contact) throws SQLException {
        List<IUser> userList = new ArrayList<>();
        List<Object> parameterList = new ArrayList<>();
        int userId = 0;
        try {
            parameterList.add(firstName);
            parameterList.add(lastName);
            parameterList.add(email);
            parameterList.add(password);
            parameterList.add(contact);

            ResultSet resultSet = database.executeProcedure("CALL register_user(?, ?, ?, ?, ?)", parameterList);
            if (resultSet != null) {
                while (resultSet.next()) {
                    userId = resultSet.getInt("user_id");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            database.closeConnection();
        }
        return userId;
    }

    public int verifyUser(String email) throws SQLException {
        List<Object> parameterList = new ArrayList<>();
        int userId = 0;
        try {
            parameterList.add(email);
            ResultSet resultSet = database.executeProcedure("CALL get_user_credential(?)", parameterList);
            if (resultSet != null) {
                while (resultSet.next()) {
                    return resultSet.getInt("user_id");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            database.closeConnection();
        }
        return userId;
    }

    @Override
    public String getUserPassword(String email) throws SQLException {
        List<Object> parameterList = new ArrayList<>();
        String password = null;
        try {
            parameterList.add(email);
            ResultSet resultSet = database.executeProcedure("CALL get_user_credential(?)", parameterList);
            if (resultSet != null) {
                while (resultSet.next()) {
                    password = resultSet.getString("password");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            database.closeConnection();
        }
        return password;
    }
    @Override
    public boolean updatePassword(String email, String password) {
        List<Object> parameterList = new ArrayList<>();
        boolean passwordUpdated = false;
        try {
            parameterList.add(email);
            parameterList.add(password);
            ResultSet resultSet = database.executeProcedure("CALL update_user_password(?,?)", parameterList);
            if (resultSet != null) {
                passwordUpdated = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                database.closeConnection();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return passwordUpdated;
    }

    @Override
    public boolean resetPassword(String email) {
        List<Object> parameterList = new ArrayList<>();
        parameterList.add(email);
        boolean userExist = false;
        try {
            ResultSet resultSet = database.executeProcedure("CALL check_user_exist(?)", parameterList);
            if (resultSet != null) {
                while(resultSet.next()){
                    userExist = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                database.closeConnection();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return userExist;
    }
    @Override
    public String getUserFirstName(int userId) {
        List<Object> parameterList = new ArrayList<>();
        parameterList.add(userId);
        String name = null;
        try {
            ResultSet resultSet = database.executeProcedure("CALL get_user_firstname(?)", parameterList);
            if (resultSet != null) {
                while (resultSet.next()){
                    name = resultSet.getString("first_name");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                database.closeConnection();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return name;
    }
}



