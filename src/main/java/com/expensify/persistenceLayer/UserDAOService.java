package com.expensify.persistenceLayer;
import com.expensify.database.IDatabase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class UserDAOService implements IUserDAOService {
    private IDatabase database;

    public UserDAOService(IDatabase database) {
      //  this.database = database;
        this.database = database;
    }
    @Override
    public int saveUser(String firstName, String lastName, String email, String password, String contact) throws SQLException {
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

    @Override
    public String encode(String password) {

        return password;
    }
    public int verifyUser(String firstName, String lastName, String email, String password, String contact) throws SQLException {
        List<Object> parameterList = new ArrayList<>();
        int userId = 0;
        try {
            parameterList.add(email);

            ResultSet resultSet = database.executeProcedure("CALL get_user_credential(?)", parameterList);
            if (resultSet != null) {
                while (resultSet.next()) {
                    if (resultSet.getString("password").equals(password)) {
                        userId = resultSet.getInt("user_id");
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            database.closeConnection();
        }
        return userId;
    }

    public boolean updatePassword(String email, String password)  {
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
    public boolean checkIfEmailExists(String email) {
        List<Object> parameterList = new ArrayList<>();
        parameterList.add(email);
        boolean userExist = false;
        try {
            ResultSet resultSet = database.executeProcedure("CALL check_user_exist(?)", parameterList);
            if (resultSet != null) {
               userExist = true;
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



}
